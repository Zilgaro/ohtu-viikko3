package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author Zilgaro
 */
public class FileUserDao implements UserDao {

    private Scanner scanner;
    private File file;
    private FileWriter fw;

    public FileUserDao(String path) throws FileNotFoundException, IOException {
        this.file = new File(path);
        this.fw = new FileWriter(path, true);
        scanner = new Scanner(file);
    }

    @Override
    public List<User> listAll() {
        ArrayList<User> users = new ArrayList<User>();
        while (scanner.hasNextLine()) {
            String read[] = scanner.nextLine().split(" ");
            User u = new User(read[0], read[1]);
            users.add(u);
        }
        return users;
    }

    @Override
    public User findByName(String name) {
        while (scanner.hasNextLine()) {
            String read[] = scanner.nextLine().split(" ");
            if (read[0].equalsIgnoreCase(name)) {
                return new User(read[0], read[1]);
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        String name = user.getUsername();
        String password = user.getPassword();

        try {
            fw.write(name + " " + password + "\n");
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
