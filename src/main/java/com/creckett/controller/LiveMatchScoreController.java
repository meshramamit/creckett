package com.creckett.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.creckett.constant.SessionAttributes;
import com.creckett.dto.BatsmanScore;
import com.creckett.dto.BowlerPerformance;
import com.creckett.dto.MiniScorecard;
import com.creckett.dto.OverRuns;
import com.creckett.dto.ScoreUpdate;
import com.creckett.exception.AuthenticationException;
import com.creckett.messaging.application.MessagingAdaptor;
import com.creckett.model.LiveMatchCommentary;
import com.creckett.model.LiveMatchScore;
import com.creckett.model.User;
import com.creckett.service.LiveMatchScoreService;
import com.creckett.service.MatchService;

public class LiveMatchScoreController extends MultiActionController {
	

	private LiveMatchScoreService liveMatchScoreService;
	
	private MessagingAdaptor messagingAdaptor;
	
	private MatchService matchService;
	

	public MatchService getMatchService() {
		return matchService;
	}


	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}


	public MessagingAdaptor getMessagingAdaptor() {
		return messagingAdaptor;
	}


	public void setMessagingAdaptor(MessagingAdaptor messagingAdaptor) {
		this.messagingAdaptor = messagingAdaptor;
	}


	public ModelAndView getLiveMatchScore(HttpServletRequest request,
		    HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		LiveMatchScore score = null;
		List<LiveMatchCommentary> liveCommentary = new ArrayList<LiveMatchCommentary>();
		
		User user = (User) request.getSession().getAttribute(SessionAttributes.USER);
		
//		user = UserPreferenceController.getHardCodedUser();
		
		if (user != null) {
			if( request.getParameter("matchId") != null ){
				Long matchId = Long.valueOf(request.getParameter("matchId"));
				
				score = liveMatchScoreService.getLatestScore(matchId);

				model.put("score", score);
				
				if(request.getParameter("commentaryId")!=null){
					Long commentaryId = Long.valueOf(request.getParameter("commentaryId"));

					liveCommentary = liveMatchScoreService.getCommentary(matchId, commentaryId);

					model.put("commentaries", liveCommentary);
				}
			}
		}else{
			throw new AuthenticationException("INVALID USER");
		}
		
		return new ModelAndView("liveMatchScore", model);
	}
	
	public ModelAndView updateLiveMatchScore(HttpServletRequest request,
		    HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String isAdmin = (String) request.getSession().getAttribute("isAdmin");
		if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
			ScoreUpdate scoreUpdate = createScoreUpdate(request);
			// Send the score update to all connected clients through messaging adaptor
			messagingAdaptor.pushScoreUpdate(scoreUpdate);
			matchService.submitBetForVirtualUser(scoreUpdate, Integer.valueOf(request.getParameter("ball")));
			model.put("status", 1);
		}else{
			model.put("status", 0);
		}
		return new ModelAndView("genericStatus", model);
	}

	private ScoreUpdate createScoreUpdate(HttpServletRequest request) {
		ScoreUpdate scoreUpdate = new ScoreUpdate();
		scoreUpdate.setMatchId(Long.valueOf(request.getParameter("matchId")));
		scoreUpdate.setOverRuns(createOverRuns(request));
		scoreUpdate.setMiniScoreCard(createMiniScorecard(request));
		return scoreUpdate;
		
	}

	private MiniScorecard createMiniScorecard(HttpServletRequest request) {
		MiniScorecard miniScorecard = new MiniScorecard();
		miniScorecard.setTeam1Score(request.getParameter("team1Score"));
		miniScorecard.setTeam2Score(request.getParameter("team2Score"));
		miniScorecard.setBatsman1Score(createBatsmanScore(request.getParameter("batsman1Name"), request.getParameter("batsman1Score")));
		miniScorecard.setBatsman2Score(createBatsmanScore(request.getParameter("batsman2Name"), request.getParameter("batsman2Score")));
		miniScorecard.setPartnership(request.getParameter("partnership"));
		miniScorecard.setCrr(Double.valueOf(request.getParameter("crr")));
		miniScorecard.setBowler1Performance(createBowlerPerformance(request.getParameter("bowler1Name"), request.getParameter("bowler1Score")));
		miniScorecard.setBowler2Performance(createBowlerPerformance(request.getParameter("bowler2Name"), request.getParameter("bowler2Score")));
		miniScorecard.setLastBatsman(request.getParameter("lastBatsman"));
		miniScorecard.setStatus(request.getParameter("status"));
		return miniScorecard;
	}


	private BowlerPerformance createBowlerPerformance(String name,
			String score) {
		BowlerPerformance bowlerPerformance = new BowlerPerformance();
		bowlerPerformance.setName(name);
		bowlerPerformance.setScore(score);
		return bowlerPerformance;
	}


	private BatsmanScore createBatsmanScore(String name, String score) {
		BatsmanScore batsmanScore = new BatsmanScore();
		batsmanScore.setName(name);
		batsmanScore.setScore(score);
		return batsmanScore;
	}


	private OverRuns createOverRuns(HttpServletRequest request) {
		OverRuns overRuns = (OverRuns) request.getSession().getAttribute("overRuns");
		int over = Integer.valueOf(request.getParameter("over"));
		int ballNumber = Integer.valueOf(request.getParameter("ball"));
		int currentInning = Integer.valueOf(request.getParameter("currentInning"));
		// Recreate overRuns if it has never been put into session OR overNumber is different from that in the session
		// OR session is changed
		if (overRuns == null || (over==overRuns.getOverNumber() && ballNumber == 1) || currentInning!=overRuns.getSession()){
			overRuns = new OverRuns();
		}
		overRuns.setOverNumber(Integer.valueOf(request.getParameter("over")));
		String runs = request.getParameter("runs");
		setBallRuns (over,overRuns,ballNumber,runs);
		overRuns.setBall(ballNumber);
		overRuns.setSession(Integer.valueOf(request.getParameter("currentInning")));
		// Put the overRuns in a session so that if user client crashes and rejoins, it still gets the runs for every ball
		// of current over being bowled
		request.getSession().setAttribute("overRuns", overRuns);
		return overRuns;
	}


	private void setBallRuns(int over, OverRuns overRuns, int ballNumber, String runs) {
		if (ballNumber == 1){
			overRuns.setRunsInBall1(runs);
		}else if (ballNumber == 2){
			overRuns.setRunsInBall2(runs);
		}else if (ballNumber == 3){
			overRuns.setRunsInBall3(runs);
		}else if (ballNumber == 4){
			overRuns.setRunsInBall4(runs);
		}else if (ballNumber == 5){
			overRuns.setRunsInBall5(runs);
		}else if (ballNumber == 0 && over!=0){
			overRuns.setRunsInBall6(runs);
		}
		
	}


	@Deprecated
	public ModelAndView getLatestLiveMatchScore(HttpServletRequest request,
		    HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<LiveMatchScore> score = new ArrayList<LiveMatchScore>();
		User user = (User) request.getSession().getAttribute(
				SessionAttributes.USER);
		if (user == null) {
			throw new AuthenticationException("INVALID USER");
		}
		
		if( request.getParameter("matchId") != null ){
			LiveMatchScore liveMatchScore = liveMatchScoreService.getLatestScore(Long.valueOf(request.getParameter("matchId")));
			if( liveMatchScore != null ){
				score.add(liveMatchScore);	
			}
		}
		model.put("scores", score);
		return new ModelAndView("liveMatchScore", model);
	}
	

	
	
	@Deprecated
	public ModelAndView addLiveMatchScore(HttpServletRequest request,
		    HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String isAdmin = (String) request.getSession().getAttribute("isAdmin");
		if (isAdmin != null && isAdmin.equalsIgnoreCase("true")) {
			if( request.getParameter("matchId") != null ){
				Long matchId = Long.valueOf(request.getParameter("matchId"));
				Double over = request.getParameter("over")!=null?Double.valueOf(request.getParameter("over")):0.0;
				String score = request.getParameter("score")!=null?request.getParameter("score"):"";
				String commentary = request.getParameter("commentary")!=null?request.getParameter("commentary"):"";
				liveMatchScoreService.addScore(matchId, over, score, commentary);
			}
			model.put("status", 1);
		}else{
			model.put("status", 0);
		}
		return new ModelAndView("genericStatus", model);
	}

	
	@Deprecated
	public ModelAndView getLiveMatchScoreOld(HttpServletRequest request,
		    HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<LiveMatchScore> score = new ArrayList<LiveMatchScore>();
		User user = (User) request.getSession().getAttribute(
				SessionAttributes.USER);
		if (user == null) {
			throw new AuthenticationException("INVALID USER");
		}
		
		if( request.getParameter("matchId") != null ){
			if( request.getParameter("over") != null ){
				score = liveMatchScoreService.getScore(Long.valueOf(request.getParameter("matchId")),
								Double.valueOf(request.getParameter("over")));
			}else{
				score = liveMatchScoreService.getScore(Long.valueOf(request.getParameter("matchId")));
			}
		}
		if( score != null && score.size() > 0 ){
			model.put( "mainScore" , score.get(score.size()-1));
		}
		model.put("scores", score);
		return new ModelAndView("liveMatchScore", model);
	}
	
	
	public LiveMatchScoreService getLiveMatchScoreService() {
		return liveMatchScoreService;
	}

	public void setLiveMatchScoreService(LiveMatchScoreService liveMatchScoreService) {
		this.liveMatchScoreService = liveMatchScoreService;
	}

}

