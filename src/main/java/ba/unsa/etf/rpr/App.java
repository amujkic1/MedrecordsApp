package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.DoctorManager;
import ba.unsa.etf.rpr.business.PatientManager;
import ba.unsa.etf.rpr.business.RecordManager;
import ba.unsa.etf.rpr.domain.Appointments;
import ba.unsa.etf.rpr.domain.Patients;
import ba.unsa.etf.rpr.domain.Records;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static final Option appointments = new Option("doc", "get-doctors", false, "Print all doctors");
    private static final Option findRecord = new Option("rec", "get-record", false, "Find record by patient id");
    private static final Option addRecord = new Option("addr", "add-record", false, "Add new record");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar medrecords-cli-jar-with-dependencies.jar [option] 'something else if needed'");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions(){
        Options options = new Options();
        options.addOption(appointments);
        options.addOption(findRecord);
        options.addOption(addRecord);
        return options;
    }

    public static void main(String[] args) throws Exception{
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cl = commandLineParser.parse(options, args);

        DoctorManager dm = new DoctorManager();

        if((cl.hasOption(appointments.getOpt()) || cl.hasOption(appointments.getLongOpt())) && cl.hasOption((appointments.getLongOpt()))){
            List<Appointments> appointments = new ArrayList<>();

            try{
                dm.getAll().forEach(a -> System.out.println(a.getUsername()));
            }catch (Exception e){
                System.out.println("No doctors");
                System.exit(1);
            }
        }else if(cl.hasOption(findRecord.getOpt()) || cl.hasOption(findRecord.getLongOpt())){

            try {
                String username = cl.getArgList().get(0);
                RecordManager recordManager = new RecordManager();
                Records record = recordManager.findUserRecord(username);
                System.out.println(record.getDiagnosis());
            }catch (Exception e){
                System.out.println("No record");
            }

        }else if(cl.hasOption(addRecord.getOpt()) || cl.hasOption(addRecord.getLongOpt())){

            try {
                String username = cl.getArgList().get(0);
                Double height = Double.valueOf(cl.getArgList().get(1));
                Double weight = Double.valueOf(cl.getArgList().get(2));
                String blood = cl.getArgList().get(3);
                RecordManager recordManager = new RecordManager();
                PatientManager patientManager = new PatientManager();
                Patients patient = patientManager.findByUsername(username);
                Records record = new Records(patient.getId(), 1, " ", " ", " ", height, weight, blood);
                recordManager.add(record);
                System.out.println("Added successfully");
            }catch (Exception e){
                System.out.println("Something went wrong with record");
            }

        }
        else{
            printFormattedOptions(options);
            System.exit(-1);
        }

    }

}