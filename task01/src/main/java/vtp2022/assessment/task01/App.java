package vtp2022.assessment.task01;

public class App {
    public static void main(String[] args) {
        try {
            String csv = "./" + args[0];
            String template = "./" + args[1];

            // template #1
            if (template.equals("./thankyou.txt")) {
                ThankYouHandler tyh = new ThankYouHandler(csv, template);
                tyh.getTemplate();
            }

            // template #2
            if (template.equals("./tour_packages.txt")) {
                TourPackagesHandler tph = new TourPackagesHandler(csv, template);
                tph.getTemplate();
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please provide a csv file followed by template file by following format:");
            System.out.println("<CSV file><space><template file>");
        }
    }
}

// Run commands:
// mvn compile exec:java -Dexec.mainClass="vtp2022.assessment.task01.App"
// -Dexec.args="thankyou.csv thankyou.txt"

// mvn compile exec:java -Dexec.mainClass="vtp2022.assessment.task01.App"
// -Dexec.args="tour_packages.csv tour_packages.txt"
