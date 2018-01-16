package rnd.auth.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rnd.auth.entity.User;
import rnd.auth.repository.UserRepository;

import java.io.*;
import java.security.Principal;
import java.sql.ResultSet;
import java.util.*;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("lkjtknlrkjnhlkrjn");
        return userRepository.findUserByUsername(username);
    }

    public User loadUserByUsernameAndPassword(String username, String password){
        User user = userRepository.findUserByUsername(username);
        user.setPassword(password);
        return user;
    }

    public String exportToCsv(Principal user) {
        User user1 = userRepository.findUserByUsername(user.getName());
        if (!user1.getRole().equals("admin")) {
            return "BAD";
        }
        final String FILE_NAME = "Users.csv";
        final Object[] FILE_HEADER = {"id", "username", "role", "staffer_id"};

        List<User> users = userRepository.findAll();

        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
        try {
            //initialize FileWriter object
            fileWriter = new FileWriter(FILE_NAME);
            //initialize CSVPrinter object
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            //Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER);

            //Write a new student object list to the CSV file
            for (User user2 : users) {
                List userDataRecord = new ArrayList();
                userDataRecord.add(String.valueOf(user2.getId()));
                userDataRecord.add(user2.getUsername());
                userDataRecord.add(user2.getRole());
                userDataRecord.add(String.valueOf(user2.getStafferId()));
                csvFilePrinter.printRecord(userDataRecord);
            }
            System.out.println("CSV file was created successfully !!!");
            return "CSV file was created successfully !!!";
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
            return "Error in CsvFileWriter !!!";
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
    }

    public String importFromCsv(Principal user){
        User user1 = userRepository.findUserByUsername(user.getName());
        if (!user1.getRole().equals("admin")) {
            return "BAD";
        }
        final String FILE_NAME = "Users.csv";
        final String[] FILE_HEADER = {"id", "username", "role", "staffer_id"};
        List<User> usersFromDB = userRepository.findAll();

        FileReader fileReader = null;

        CSVParser csvFileParser = null;

        CSVFormat csvFileFormat = CSVFormat.EXCEL.withHeader(FILE_HEADER);

        try {
            fileReader = new FileReader(FILE_NAME);

            csvFileParser = new CSVParser(fileReader, csvFileFormat);

            List<CSVRecord> csvRecords = csvFileParser.getRecords();

            for (int i=1; i<csvRecords.size(); i++){
                CSVRecord record = csvRecords.get(i);
                usersFromDB.get(i-1).setUsername(record.get("username"));
                usersFromDB.get(i-1).setRole(record.get("role"));
                usersFromDB.get(i-1).setStafferId(Long.parseLong(record.get("staffer_id")));
            }
            return "OK";
        }
        catch (Exception e){
            System.out.println("Error");
            e.printStackTrace();
            return "Error";
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e){
                System.out.println("Error");
                e.printStackTrace();
                return "Error";
            }
        }
    }
}