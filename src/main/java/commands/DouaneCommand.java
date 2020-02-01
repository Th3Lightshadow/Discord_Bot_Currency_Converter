package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static auxiliary.DecimalCorrection.decimalCorrect;
import static auxiliary.DecimalCorrection.round;
import static auxiliary.MoneyConvertion.convert;

public class DouaneCommand extends Command {

    public DouaneCommand()
    {
        this.name = "Douane";
        this.help = "Calculate 'inklaringskosten' and 'btw' roughly";
    }

    @Override
    protected void execute(CommandEvent event) {
        System.out.println("Douane Command Called");
        String[] input = event.getArgs().split("\\s+");
        if (input.length == 1) {
            String replyString = String.format("Douane Command roughly calculates 'inklaringskosten' and 'btw'. %n Convert X currency to EUR and calculate the 'invoerrechten' and 'btw' roughly. %n Expected input: Amount (including shipping) OriginCurrency.");
            event.reply(replyString);
        } else {
            float inputAmount = Float.parseFloat(input[0]);
            String fromCurrency = input[1].toUpperCase();
            float converted = convert(inputAmount, fromCurrency,  "EUR");
            if (converted == -1) {
                String replyString = String.format("Wrong currency code used. %n Supported currencies USD, JPY, BGN, CZK, DKK, GBP, HUF, PLN, RON, SEK, CHF, ISK, NOK, HRK, RUB, TRY, AUD, BRL, CAD, CNY, HKD, IDR, ILS, INR, KRW, MXN, MYR, NZD, PHP, SGD, THB, ZAR and EUR.");
                event.reply(replyString);
            } else {
                if (converted <= 22) {
                    converted = round(converted,2);
                    String convertedString = decimalCorrect(converted);
                    String replyString = String.format("Calculated Douane cost roughly for %s %s to %s %n %s: %s (Should have no extra costs because it is less than 22 Euro's)", decimalCorrect(inputAmount), fromCurrency, "EUR", "EUR", convertedString);
                    event.reply(replyString);
                } else if (converted <= 150){
                    float convertedExtra = (float) ((converted * 1.21) + 13);
                    converted = round(converted,2);
                    convertedExtra = round(convertedExtra, 2);
                    String convertedString = decimalCorrect(converted);
                    String convertedExtraString = decimalCorrect(convertedExtra);
                    String replyString = String.format("Calculated Douane cost roughly for %s %s to %s %n %s: (%s * 1.21) + 13 = %s", decimalCorrect(inputAmount), fromCurrency, "EUR", "EUR", convertedString, convertedExtraString);
                    event.reply(replyString);
                } else {
                    float convertedExtra = (float) ((converted * 1.21) + 13);
                    converted = round(converted,2);
                    convertedExtra = round(convertedExtra, 2);
                    String convertedString = decimalCorrect(converted);
                    String convertedExtraString = decimalCorrect(convertedExtra);
                    String replyString = String.format("Calculated Douane cost roughly for %s %s to %s %n %s: (%s * 1.21) + 13 = %s (It is possible that 'invoerrechten' have to be paid because it is more than 150 Euro's)", decimalCorrect(inputAmount), fromCurrency, "EUR", "EUR", convertedString, convertedExtraString);
                    event.reply(replyString);
                }
            }
        }
        System.out.println("Douane End");
    }
}
