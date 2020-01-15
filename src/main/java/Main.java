import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.examples.command.AboutCommand;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import commands.*;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, IOException {
        List<String> list = Files.readAllLines(Paths.get("C:/Users/20172410/IdeaProjects/Discord_Bot_Currency_Converter/src/main/resources/config.txt"));
        String token = list.get(0);
        String ownerId = list.get(1);

        EventWaiter waiter = new EventWaiter();

        CommandClientBuilder client = new CommandClientBuilder();

        client.useDefaultGame();

        client.setOwnerId(ownerId);

        client.setPrefix("!");

        client.addCommands(
            new AboutCommand(Color.BLUE, "a Currency Converter Bot",
                    new String[]{"Commands"}, new Permission[]{Permission.ADMINISTRATOR}),

            new HelloCommand(waiter)
         );

        LocalDateTime lastChange = LocalDateTime.now();

        new JDABuilder(AccountType.BOT)
                .setToken(token)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing("Loading..."))
                .addEventListeners(waiter, client.build())
                .build();
    }
}
