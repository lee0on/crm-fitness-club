package javaproject;

import java.io.*;
import java.util.LinkedList;

public class FileHandler {

    public LinkedList<Member> readFile() throws IOException {
        LinkedList<Member> m = new LinkedList<>();
        String lineRead;
        String[] splitLine;
        Member member;

        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))){
            lineRead = reader.readLine();

            while (lineRead != null){
                if (lineRead.trim().isEmpty()) {
                    lineRead = reader.readLine();
                    continue;
                }
                splitLine = lineRead.split(",");
                if (splitLine[0].equals("S")){
                    SingleClubMember mem = new SingleClubMember('S', Integer.parseInt(splitLine[1]), splitLine[2], Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                    m.add(mem);
                } else{
                    MultiClubMember mem = new MultiClubMember('M', Integer.parseInt(splitLine[1]), splitLine[2], Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                    m.add(mem);
                }
                lineRead = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException(e);
        }
        return m;
    }

    public void appendFile(String mem) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv", true))){
            writer.write(mem + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException(e);
        }
    }

    public void overwriteFile(LinkedList<Member> m) throws Exception {
        String s;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.temp", false))){
            for (int i = 0; i < m.size(); i++){
                s = m.get(i).toString();
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new IOException(e);
        }

        try {
            File file = new File("members.csv");
            File tempFile = new File("members.temp");

            file.delete();
            tempFile.renameTo(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        }
    }
}