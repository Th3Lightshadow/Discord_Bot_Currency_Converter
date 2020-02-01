package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import static auxiliary.DecimalCorrection.decimalCorrect;
import static auxiliary.DecimalCorrection.round;
import static auxiliary.MoneyConvertion.convert;

public class ConvertCommand extends Command {

    public ConvertCommand()
    {
        this.name = "convert";
        this.help = "Converts Currencies";
        this.aliases = new String[]{"conv"};
    }

    @Override
    protected void execute(CommandEvent event) {
        System.out.println("Convert Command Called");
        String[] input = event.getArgs().split("\\s+");
        if (input.length == 1) {
            String replyString = String.format("Convert command supports two mode of operating. %n First mode convert X currency to Y currency. %n Expected input: Amount OriginCurrency WantedCurrency. %n %n Second mode convert X currency to JPY, EUR, USD, GBP, AUD, and CAD. %n Expected input: Amount OriginCurrency");
            event.reply(replyString);
            System.out.println("Succeeded Info");
        } else {
            float inputAmount = Float.parseFloat(input[0]);
            String fromCurrency = input[1].toUpperCase();
            if (input.length == 2) {
                String[] conversions = new String[6];
                float yenCon = convert(inputAmount, fromCurrency, "JPY");
                if (yenCon == -1) {
                    String replyString = String.format("Wrong currency code used. %n Supported currencies USD, JPY, BGN, CZK, DKK, GBP, HUF, PLN, RON, SEK, CHF, ISK, NOK, HRK, RUB, TRY, AUD, BRL, CAD, CNY, HKD, IDR, ILS, INR, KRW, MXN, MYR, NZD, PHP, SGD, THB, ZAR and EUR.");
                    event.reply(replyString);
                    System.out.println("Failed Multiple Conversion");
                } else {
                    yenCon = round(yenCon, 2);
                    conversions[0] = decimalCorrect(yenCon);
                    float eurCon = convert(inputAmount, fromCurrency, "EUR");
                    eurCon = round(eurCon, 2);
                    conversions[1] = decimalCorrect(eurCon);
                    float usdCon = convert(inputAmount, fromCurrency, "USD");
                    usdCon = round(usdCon, 2);
                    conversions[2] = decimalCorrect(usdCon);
                    float gbpCon = convert(inputAmount, fromCurrency, "GBP");
                    gbpCon = round(gbpCon, 2);
                    conversions[3] = decimalCorrect(gbpCon);
                    float audCon = convert(inputAmount, fromCurrency, "AUD");
                    audCon = round(audCon, 2);
                    conversions[4] = decimalCorrect(audCon);
                    float cadCon = convert(inputAmount, fromCurrency, "CAD");
                    cadCon = round(cadCon, 2);
                    conversions[5] = decimalCorrect(cadCon);
                    String replyString = String.format("Converted %s %s to JPY, EUR, USD, GBP, AUD and CAD. %n %s: %s %n %s: %s %n %s: %s %n %s: %s %n %s: %s %n %s: %s %n",
                            decimalCorrect(inputAmount), fromCurrency, "JPY", conversions[0], "EUR", conversions[1],
                            "USD", conversions[2], "GBP", conversions[3], "AUD", conversions[4], "CAD", conversions[5]);
                    event.reply(replyString);
                    System.out.println("Succeeded Multiple Conversion");
                }
            } else {
                String toCurrency = input[2].toUpperCase();
                float converted = convert(inputAmount, fromCurrency, toCurrency);
                if (converted == -1) {
                    String replyString = String.format("Wrong currency code used. %n Supported currencies USD, JPY, BGN, CZK, DKK, GBP, HUF, PLN, RON, SEK, CHF, ISK, NOK, HRK, RUB, TRY, AUD, BRL, CAD, CNY, HKD, IDR, ILS, INR, KRW, MXN, MYR, NZD, PHP, SGD, THB, ZAR and EUR.");
                    event.reply(replyString);
                    System.out.println("Failed Single Conversion");
                } else {
                    converted = round(converted, 2);
                    String convertedString = decimalCorrect(converted);
                    String replyString = String.format("Converted %s %s to %s %n %s: %s", decimalCorrect(inputAmount), fromCurrency, toCurrency, toCurrency, convertedString);
                    event.reply(replyString);
                    System.out.println("Succeeded Single Conversion");
                }
            }
        }
        System.out.println("Convert End");
    }
}
