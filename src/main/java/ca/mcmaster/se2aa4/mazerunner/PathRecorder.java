package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

public class PathRecorder implements Observer{
    private StringBuilder path = new StringBuilder();

    public void update(String command){
        path.append(command);
    }

    public String getCanonicalPath(){
        return path.toString();
    }

    public String getFactorizedPath(){
        return toFactorized(getCanonicalPath());
    }

    public String toFactorized(String path){
        int index = 0;
        String str = "";
        while(index < path.length()){
            int numSame = 0;
            for(int i = index; i < path.length(); i++){
                if(path.charAt(index) != path.charAt(i)){
                    break;
                }else{
                    numSame++;
                }
            }
            str += numSame +""+ path.charAt(index);
            index += numSame;
        }
        return str;
    }
}