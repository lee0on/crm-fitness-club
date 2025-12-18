package project.fcl.service;

import project.fcl.model.Member;
import project.fcl.model.MultiClubMember;
import project.fcl.model.SingleClubMember;

import java.io.*;
import java.util.LinkedList;

public class FileHandler {

    public LinkedList<Member> readFile() {
        LinkedList<Member> m = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))) {
            String lineRead;

            while ((lineRead = reader.readLine()) != null) {

                if (lineRead.trim().isEmpty()) {
                    continue;
                }

                String[] splitLine = lineRead.split(",");

                if (splitLine[0].equals("S")) {
                    m.add(new SingleClubMember(
                            'S',
                            Integer.parseInt(splitLine[1]),
                            splitLine[2],
                            Double.parseDouble(splitLine[3]),
                            Integer.parseInt(splitLine[4])
                    ));
                } else {
                    m.add(new MultiClubMember(
                            'M',
                            Integer.parseInt(splitLine[1]),
                            splitLine[2],
                            Double.parseDouble(splitLine[3]),
                            Integer.parseInt(splitLine[4])
                    ));
                }
            }

        } catch (IOException e) {
            System.out.println("Cannot read file: members.csv: " + e.getMessage());
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