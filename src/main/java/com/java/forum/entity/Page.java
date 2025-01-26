package com.java.forum.entity;

/*
 * Related to tabs
 * */

public class Page {

    //the current page number
    private int currentPageNum = 1;
    //display limit:
    private int postDisplayLimit = 10;
    // the total number of discussPosts (to calculate the total number of pages)
    private int postTotalCount;
    //the path to the specific path
    private String tabPath;

//    private int totalPageCount;

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        int endPageNum = getEndPageNum();
        if (currentPageNum == endPageNum) {
            this.currentPageNum = endPageNum;
        } else if (currentPageNum >= 1) {
            this.currentPageNum = currentPageNum;
        }
    }

    public int getPostDisplayLimit() {
        return postDisplayLimit;
    }

    public void setPostDisplayLimit(int postDisplayLimit) {
        if (postDisplayLimit >= 1 && postDisplayLimit <= 100) {
            this.postDisplayLimit = postDisplayLimit;
        }
    }

    public int getPostTotalCount() {
        return postTotalCount;
    }

    public void setPostTotalCount(int postTotalCount) {
        if (postTotalCount >= 0) {
            this.postTotalCount = postTotalCount;
        }
    }

    public String getTabPath() {
        return tabPath;
    }

    public void setTabPath(String tabPath) {
        this.tabPath = tabPath;
    }


    //Get the starting post of the current tab
    public int getOffset() {
        //(currentPageNum-1) * postDisplayLimit
        return (currentPageNum - 1) * postDisplayLimit;
    }

    public int getTotalPageCount() {
        // postTotalCount/postLimitDisplay (maybe+1)
        if (postTotalCount % postDisplayLimit == 0) {
            return postTotalCount / postDisplayLimit;
        } else {
            return (postTotalCount / postDisplayLimit) + 1;
        }
    }

    //the starting page number
    public int getStartPageNum() {
        int startPageNum = currentPageNum - 2;
        return Math.max(startPageNum, 1);
    }

    public int getEndPageNum() {
        int endPageNum = currentPageNum + 2;
        int totalPageCount = getTotalPageCount();
        return Math.min(endPageNum, totalPageCount); //Can't larger than totalPageCount

    }

}
