package org.dubbel7.xch.matching.book;

import java.util.List;

public class MatchResult {

    private final long remainingSize;
    private final List<Match> matchList;

    public MatchResult(long remainingSize, List<Match> matchList) {
        this.remainingSize = remainingSize;
        this.matchList = matchList;
    }

    public long getRemainingSize() {
        return remainingSize;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "remainingSize=" + remainingSize +
                ", matchList=" + matchList +
                '}';
    }
}
