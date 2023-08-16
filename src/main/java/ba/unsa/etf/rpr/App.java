package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.DestinationManager;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Destination;
import ba.unsa.etf.rpr.exceptions.AppException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Option addDestination = new Option("d", "add-destination",  false, "Adding new destination to the database");
    private static final Option addCategory = new Option("c", "add-category", false, "Adding new category to the database");
    private static final Option getDestinations = new Option("getD", "view-destinations",  false, "Printing all quotes from the database");
    private static final Option getCategories = new Option("getC", "view-categories", false, "Printing all categories from the database");
    private static final Option categoryDefinition = new Option(null, "category",false, "Defining category for next added quote");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar quote-maker.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addDestination);
        options.addOption(addCategory);
        options.addOption(getDestinations);
        options.addOption(getCategories);
        options.addOption(categoryDefinition);
        return options;
    }

    public static Category searchThroughCategories(List<Category> listOfCategories, String categoryName) {
        Category category;
        category = listOfCategories.stream().filter(cat -> cat.getName().toLowerCase().equals(categoryName.toLowerCase())).findAny().get();
        return category;
    }

    public static void main( String[] args ) throws ParseException, AppException {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);



        if((cl.hasOption(addDestination.getOpt()) || cl.hasOption(addDestination.getLongOpt())) && cl.hasOption((categoryDefinition.getLongOpt()))){
            DestinationManager destinationManager = new DestinationManager();
            CategoryManager categoryManager = new CategoryManager();
            Category category = null;
            try {
                category = searchThroughCategories(categoryManager.getAll(), cl.getArgList().get(1));
            }catch(Exception e) {
                System.out.println("There is no category in the list! Try again.");
                System.exit(1);
            }

            String name = cl.getArgList().get(0);
            String location = cl.getArgList().get(1);
            String description = cl.getArgList().get(2);
            Double rating = Double.valueOf(cl.getArgList().get(3));
            Destination destination = new Destination(0, name, location, description, category.getId(), rating);
            destinationManager.add(destination);
            System.out.println("You successfully added quote to database!");
        } else if(cl.hasOption(getDestinations.getOpt()) || cl.hasOption(getDestinations.getLongOpt())){
            DestinationManager destinationManager = new DestinationManager();
            destinationManager.getAll().forEach(d -> System.out.println(d.getName()));
        }
        else if(cl.hasOption(addCategory.getOpt()) || cl.hasOption(addCategory.getLongOpt())){
            try {
                CategoryManager categoryManager = new CategoryManager();
                Category cat = new Category();
                cat.setName(cl.getArgList().get(0));
                categoryManager.add(cat);
                System.out.println("Category has been added successfully");
            }catch(Exception e) {
                System.out.println("There is already category with same name in database! Try again");
                System.exit(1);
            }
        } else if(cl.hasOption(getCategories.getOpt()) || cl.hasOption(getCategories.getLongOpt())){
            CategoryManager categoryManager = new CategoryManager();
            categoryManager.getAll().forEach(c -> System.out.println(c.getName()));
        } else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }
}
