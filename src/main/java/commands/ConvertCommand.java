package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class ConvertCommand extends Command {

    public ConvertCommand()
    {
        this.name = "convert";
        this.help = "converts currencies";
    }

    @Override
    protected void execute(CommandEvent event) {

    }
}
