class Solution {
    static int toSec(String time){
        String[] t = time.split(":");
        return Integer.parseInt(t[0])*60+Integer.parseInt(t[1]);
    }
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int totalSec = toSec(video_len);
        int cur = toSec(pos);
        int opS = toSec(op_start);
        int opE = toSec(op_end);
        
        if(opS<=cur&&cur<=opE){
            cur = opE;
        }
        for(String cmd:commands){
            switch(cmd){
            case "prev":
                cur = Math.max(cur-10,0);
                break;
            case "next":
                cur = Math.min(cur+10,totalSec);
                break;
            }
            
            if(opS<=cur&&cur<=opE){
            cur = opE;
            }
        }
        return String.format("%02d:%02d",cur/60, cur%60);
    }
}