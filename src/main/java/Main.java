import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, IOException {
        List<String> list = Files.readAllLines(Paths.get("config.txt"));

        String token = list.get(0);

        String ownerId = list.get(1);

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(token);
        builder.addEventListeners(new Main());
        builder.build();
    }
}
