import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Currency extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("We received a message from " +
                event.getAuthor().getName() + ":" +
                event.getMessage().getContentDisplay());
        if (event.getMessage().getContentRaw().equals("/ping")) {
            event.getChannel().sendMessage("Pong!").queue();
        }
    }
}
