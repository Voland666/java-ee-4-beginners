package demo;

import java.util.Optional;

/**
 * Created by eugene on 11/01/2017.
 */
public class Logger<T> {
    private Saver saver; //DI (+DI Framework)
    private Filter filter;

    public Logger(Saver saver, Filter filter) {
        this.saver = saver;
        this.filter = filter;
    }

    public void log(T message, int level) {
        if (filter.filter(level)) {
            saver.save(message);
        }
    }

    public T[] get10LastObjects() {
        return null;
    }

    public static <V> V m(V arg) { return null; }
    public static int m(int a) { return 0; }
}

class Saver {
    public void save(Object message) {
        ///////
    }
}

class FileSaver extends Saver {  //IS-A
    private final String pathToFile;


    public FileSaver(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public void save(Object message) {
        //.......
        super.save(message);
        //.....
    }
}

interface Filter {
    boolean filter(int level);
}

class LevelFilter implements Filter {
    @Override
    public boolean filter(int level) {
        return false;
    }
}

class BadSaver extends Saver {
    @Override
    public void save(Object message) {
        //Contract broken
    }
}


class Main {
    public static void main(String[] args) {
        Logger<String> logger = new Logger<String>(new FileSaver(""), new LevelFilter());
        logger.log("Ssssss", 9);
        logger.log("Ssssss", 9);
        logger.log(new Object(), 9);
        logger.log("Ssssss", 9);
        logger.log("Ssssss", 9);

        for (String elemant : logger.get10LastObjects()) {
                final String strElemnt = elemant;
                System.out.println(strElemnt);
        }

        Logger.m(new Integer(5));
    }
}

//POJO
class User {
    private int id;
    private String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}