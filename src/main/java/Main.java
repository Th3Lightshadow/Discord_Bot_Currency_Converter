import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.examples.command.AboutCommand;
import commands.ConvertCommand;
import commands.DouaneCommand;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException, IOException {
        Path pathToFile = Paths.get("src/main/resources/config.txt");
        List<String> list = Files.readAllLines(pathToFile);
        String token = list.get(0);
        String ownerId = list.get(1);

        EventWaiter waiter = new EventWaiter();

        CommandClientBuilder client = new CommandClientBuilder();

        client.useDefaultGame();

        client.setOwnerId(ownerId);

        client.setPrefix("!!");

        client.addCommands(
            new AboutCommand(Color.BLUE, "a Currency Converter Bot",
                    new String[]{"Commands"}, Permission.ADMINISTRATOR),

            new ConvertCommand(),

            new DouaneCommand()
         );

        new JDABuilder(AccountType.BOT)
                .setToken(token)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing("Loading..."))
                .addEventListeners(waiter, client.build())
                .build();
    }

}
