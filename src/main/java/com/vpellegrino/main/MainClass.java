package com.vpellegrino.main;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import com.vpellegrino.model.CommandLineValues;
import com.vpellegrino.model.Entity;
import com.vpellegrino.service.CSVExportService;
import com.vpellegrino.service.EntityGeneratorService;


/**
 * This is the main class used to load command line args and to invoke service
 * which provides generation of entity rows and their export
 * 
 * @author vpellegrino
 *
 */
public class MainClass {

    private static Logger logger = Logger.getLogger(MainClass.class);

    public static void main(String[] args) {
    	
        // Parsing command line arguments
        CommandLineValues cmdValues = new CommandLineValues();
        CmdLineParser parser = new CmdLineParser(cmdValues);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("List of available options:");
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();
            System.exit(1);
        }
        
        // Generating entities list
        EntityGeneratorService entityGeneratorService = new EntityGeneratorService(cmdValues.getDomains(), 
        		cmdValues.getLevels());
        logger.info("Generating domains declarations... ");
        List<Entity> declaredDomains = entityGeneratorService.declareDomains();
        logger.info("# of domains: "+declaredDomains.size());
        logger.info("Generating domains relationship... ");
        List<Entity> parentshipDomains = entityGeneratorService.setDomainsParentship();
        logger.info("# of domains relationship: "+parentshipDomains.size());
        logger.info("Generating vms... ");
        List<Entity> declaredVMs = entityGeneratorService.declareVMs();
        logger.info("# of vms: "+declaredVMs.size());
        logger.info("Generating vms relationship... ");
        List<Entity> parentshipVMs = entityGeneratorService.setVMsParentship();
        logger.info("# of vms relationship: "+parentshipVMs.size());
        List<Entity> allEntities = new ArrayList<Entity>();
        allEntities.addAll(declaredDomains); allEntities.addAll(parentshipDomains);
        allEntities.addAll(declaredVMs); allEntities.addAll(parentshipVMs);
        logger.info("total number of generated rows: "+parentshipVMs.size());
        
        // Exporting results on CSV
        logger.info("Exporting on csv file... ");
        CSVExportService csvExportService = new CSVExportService();
        String fileName = csvExportService.export(allEntities);
        logger.info("Exporting complete, with file name: "+fileName);
        
    }

}