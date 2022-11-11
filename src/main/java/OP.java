import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OP {
    public static void main(String[] args) throws IOException {
        DeleteComma deleteComma = new DeleteComma();
        deleteComma.start();
        Time time = new Time();
        time.start();
    }

    static class DeleteComma{
        public DeleteComma() {
        }

        public void start() throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/dm.txt"));
            List<String> list = new ArrayList<>();
            while(reader.ready()){
                String curString = reader.readLine();
                list.add(curString);
            }
            removeComma(list);
            reader.close();
        }

        public void removeComma(List<String> list) throws IOException {
            List<String> res = new ArrayList<>();
            for (String s : list){
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++){
                    char c = s.charAt(i);
                    if(c != ','){
                        sb.append(c);
                    }
                }
                res.add(sb.toString());
                writer(res);
            }
        }

        public void writer(List<String> res) throws IOException {
            FileWriter fw = new FileWriter("src/main/dm-output.txt");
            BufferedWriter bufw = new BufferedWriter(fw);
            for (String s : res){
                bufw.write(s+"\n");
            }
            bufw.flush();
        }
    }


    static class Time{
        public Time() {
        }

        public void start() throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/time.txt"));
            List<String> list = new ArrayList<>();
            while(reader.ready()){
                String curString = reader.readLine();
                list.add(curString);
            }
            reconstruct(list);
            reader.close();
        }

        public void reconstruct(List<String> list) throws IOException {
            List<String> res = new ArrayList<>();
            for (String s : list){
                String[] strings = s.split(" ");
                StringBuilder resString = new StringBuilder();

                String firstString = strings[1];
                StringBuilder firstStringRes = new StringBuilder();
                for(int i = 0; i < firstString.length(); i++){
                    if(firstString.charAt(i) == '-'){
                        firstStringRes.append('/');
                    }else{
                        firstStringRes.append(firstString.charAt(i));
                    }
                }

                String secondString = strings[2];
                StringBuilder secondStringRes = new StringBuilder();
                String[] strings1 = secondString.split(":");
                strings1[2] = "00";
                int num = Integer.parseInt(strings1[1]);
                if(num + 2 >= 60){
                    num = (num + 2) % 60;
                    strings1[1] = "0" + num;

                    int hour = Integer.parseInt(strings1[0]);
                    if(hour == 23){
                        strings1[0] = "00";
                    }else{
                        if(hour + 2 >= 10){
                            strings1[0] = (hour + 2) + "";
                        }else{
                            strings1[0] = "0" + (hour + 1);
                        }
                    }
                }else{
                    strings1[1] = "0" + (num + 2);
                }

                for(String s1 : strings1){
                    secondStringRes.append(s1);
                    secondStringRes.append(":");
                }
                secondStringRes.delete(secondStringRes.length() - 1, secondStringRes.length());

                resString.append(firstStringRes);
                resString.append(" ");
                resString.append(secondStringRes);
                res.add(resString.toString());
            }
            writer(res);
        }

        public void writer(List<String> res) throws IOException {
            FileWriter fw = new FileWriter("src/main/time-output.txt");
            BufferedWriter bufw = new BufferedWriter(fw);
            for (String s : res){
                bufw.write(s+"\n");
            }
            bufw.flush();
        }
    }
}
