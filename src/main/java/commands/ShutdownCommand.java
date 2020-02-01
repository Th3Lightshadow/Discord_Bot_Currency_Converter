package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;

@CommandInfo(
        name = "Shutdown",
        description = "Safely shuts down the bot. (Only works for Owner)"
)
public class ShutdownCommand extends Command {

    public ShutdownCommand()
    {
        this.name = "Shutdown";
        this.help = "safely shuts off the bot";
        this.guildOnly = false;
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reactWarning();
        String replyString = String.format("Bot will now shutdown.");
        System.out.println("Bot Shutdown");
        event.reply(replyString);
        event.getJDA().shutdown();
        System.exit(0);
    }

}