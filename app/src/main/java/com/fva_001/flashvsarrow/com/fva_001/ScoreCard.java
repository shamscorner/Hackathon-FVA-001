package com.fva_001.flashvsarrow.com.fva_001;

/**
 * Created by ShamimH on 01-Apr-16.
 */
public class ScoreCard {
    //all variable and constant here...
    private int pollutionRate, unorganizedRate, riskRate, score, finalScore;
    private int noOfPollutedTask, noOFUnorganizedTask, taskCompleted, toolsUsed;
    private int pollutionFracktion, unorganizedFracktion;
    private String time;

    // all constructor here...
    public ScoreCard(int noOfPollutedTask, int noOFUnorganizedTask){
        this.noOfPollutedTask = noOfPollutedTask;
        this.noOFUnorganizedTask = noOFUnorganizedTask;
        this.pollutionRate = 100;
        this.unorganizedRate = 100;
        this.riskRate = 100;
        this.score = 0;
        this.taskCompleted = 0;

        pollutionFracktion = 100/noOfPollutedTask;
        unorganizedFracktion = 100/noOFUnorganizedTask;
    }
    public ScoreCard(int noOfPollutedTask, int noOFUnorganizedTask, int pollutionRate, int unorganizedRate, int score, int taskCompleted){
        this.noOfPollutedTask = noOfPollutedTask;
        this.noOFUnorganizedTask = noOFUnorganizedTask;
        this.pollutionRate = pollutionRate;
        this.unorganizedRate = unorganizedRate;
        this.score = score;
        this.taskCompleted = taskCompleted;
        this.riskRate = (int)(pollutionRate*1.5+unorganizedRate*0.5)/2;

        pollutionFracktion = 100/noOfPollutedTask;
        unorganizedFracktion = 100/noOFUnorganizedTask;
    }
    public ScoreCard(int noOfPollutedTask, int noOFUnorganizedTask, int pollutionRate, int unorganizedRate, int riskRate, int score, int taskCompleted){
        this.noOfPollutedTask = noOfPollutedTask;
        this.noOFUnorganizedTask = noOFUnorganizedTask;
        this.pollutionRate = pollutionRate;
        this.unorganizedRate = unorganizedRate;
        this.riskRate = riskRate;
        this.score = score;
        this.taskCompleted = taskCompleted;

        pollutionFracktion = 100/noOfPollutedTask;
        unorganizedFracktion = 100/noOFUnorganizedTask;
    }

    //all setter and getter function are here...
    public void setNoOfPollutedTask(int x){
        noOfPollutedTask = x;
    }
    public int getNoOfPollutedTask(){
        return noOfPollutedTask;
    }

    public void setNoOFUnorganizedTask(int x){
        noOFUnorganizedTask = x;
    }
    public int getNoOFUnorganizedTask(){
        return noOFUnorganizedTask;
    }

    public void setPollutionRate(int x){
        pollutionRate = x;
    }
    public int getPollutionRate(){
        return pollutionRate;
    }

    public void setUnorganizedRate(int x){
        unorganizedRate = x;
    }
    public int getUnorganizedRate(){
        return unorganizedRate;
    }

    public void setRiskRate(int x){
        riskRate = x;
    }
    public int getRiskRate(){
        return riskRate;
    }

    public void setScore(int x){
        score = x;
    }
    public int getScore(){
        return score;
    }

    public void setFinalScore(int x){
        finalScore = x;
    }
    public int getFinalScore(){
        return finalScore;
    }

    public void setTaskCompleted(int x){
        taskCompleted = x;
    }
    public int getTaskCompleted(){
        return taskCompleted;
    }

    public void setToolsUsed(int x){
        toolsUsed = x;
    }
    public int getToolsUsed(){
        return toolsUsed;
    }

    public void setTime(String s){
        time = s;
    }
    public String getTime(){
        return time;
    }

    //all essential optional function are here....
    public void increaseScore(int x, String type){
        score = score + x;
        ++taskCompleted;
        if(type.equals("pol")){
            --noOfPollutedTask;
            pollutionRate = pollutionRate - pollutionFracktion;
        }else if(type.equals("un")){
            --noOFUnorganizedTask;
            unorganizedRate = unorganizedRate - unorganizedFracktion;
        }
        riskRate = (int) ((pollutionRate*1.5 + unorganizedRate*0.5)/2);
    }
    public void decreaseScore(int x){
        score = score - x;
    }
}
