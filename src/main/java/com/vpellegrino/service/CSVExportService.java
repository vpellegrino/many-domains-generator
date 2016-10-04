package com.vpellegrino.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.vpellegrino.model.Entity;

/**
 * This class provides export in CSV format files
 * 
 * @author vpellegrino
 *
 */
public class CSVExportService {

    private static final String DELIMITATOR = ";";
	
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * This method, creates and outputs a csv file, in which is
     * contained an high number of domains
     * 
     * @param rowsList a list containing the entity rows to be imported
     * @return exported file name
     */
    public String export(List<Entity> rowsList) {
        String fileName = "ManyDomains_"
                + DateFormatUtils.format(new Date(), "yyyyMMdd") + ".csv";
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(fileName));
            String header = StringUtils.join("TS", DELIMITATOR, "CHANGETYPE",
                    DELIMITATOR, "ENTCATNM", DELIMITATOR, "ENTNM",
                    DELIMITATOR, "DS_ENTNM", DELIMITATOR, "ENTCATNMPARENT", 
                    DELIMITATOR, "DS_ENTNMPARENT", DELIMITATOR, "ENTTYPENM");
            printWriter.println(header);
            for (Entity entity : rowsList) {
                String row = StringUtils.join(
                        entity.getTs(), DELIMITATOR,
                        entity.getChangetype(), DELIMITATOR,
                        entity.getEntcatnm(), DELIMITATOR,
                        entity.getEntnm(), DELIMITATOR,
                        entity.getDsEntnm(), DELIMITATOR,
                        entity.getEntcatnmparent(), DELIMITATOR,
                        entity.getDsEntnmparent(), DELIMITATOR,
                        entity.getEnttypenm());
                printWriter.println(row);
            }
        } catch (IOException e) {
            logger.error(e);
        } finally {
            printWriter.close();
        }
        return fileName;
    }

}
