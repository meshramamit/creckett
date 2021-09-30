package com.creckett.dao;

import java.util.Date;
import java.util.List;

import com.creckett.model.MatchMaster;

public interface MatchMasterDAO {

    public MatchMaster save(MatchMaster matchMaster);

    public MatchMaster get(Long id);

    public void delete(MatchMaster matchMaster);

    public List<MatchMaster> getListOfUpcomingMatches(Date fromDate, Date toDate);
    
    public List<MatchMaster> getListOfPlayingMatches();

}
