package vtp2022.assessment.task01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ThankYouHandler {
    private String csv;
    private String template;

    // constructor
    public ThankYouHandler(String csv, String template) {
        this.csv = csv;
        this.template = template;
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    // this method returns the csv file in rows
    public ArrayList<String> getCsvData() {
        ArrayList<String> csvRow = new ArrayList<String>();
        try {
            String line = "";
            FileReader fr = new FileReader(csv);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                csvRow.add(line);
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        return csvRow;
    }

    // individual template by row
    public void getIndividualTemplate(int i) {
        ArrayList<String> csvRow = this.getCsvData();
        try {
            String line = "";
            ArrayList<String> templateRow = new ArrayList<String>();
            BufferedReader br = new BufferedReader(new FileReader(template));
            while ((line = br.readLine()) != null) {
                templateRow.add(line);
            }

            ArrayList<String> rowContent = new ArrayList<String>();
            String[] rowContentArr = csvRow.get(i).split(",");
            for (String item : rowContentArr) {
                rowContent.add(item);
            }

            // fill in the template
            String line1 = templateRow.get(0).replace("__address__", rowContent.get(2).replace("\\n", "\n"));
            String line2 = templateRow.get(2).replace("__first_name__", rowContent.get(0));
            String line3 = templateRow.get(4).replace("__years__", rowContent.get(3));

            templateRow.set(0, line1);
            templateRow.set(2, line2);
            templateRow.set(4, line3);

            for (String item : templateRow) {
                System.out.printf("%s\n", item);
            }

            br.close();

        } catch (IndexOutOfBoundsException e) {
            System.err.println(e);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    // get the template for all rows
    public void getTemplate() {
        ArrayList<String> csvRow = this.getCsvData();
        for (int i = 1; i < csvRow.size(); i++) {
            getIndividualTemplate(i);
        }
    }
}
