package zalora.assignment.duckie.twitsplit.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import zalora.assignment.duckie.twitsplit.R;

public class Utils {
    public static String getPresentDateFromMilliseconds(long millisecond) {
        long currentTime = System.currentTimeMillis();
        long marginTime = currentTime - millisecond;

        int days = (int) (marginTime / 1000 / 3600 / 24);
        if (days > 0) {
            return String.format("%dd", days);
        }

        int hours = (int) (marginTime / 1000 /3600);
        if (hours > 0) {
            return String.format("%dh", hours);
        }

        int minutes = (int) (marginTime / 1000 / 60);
        if (minutes > 0) {
            return String.format("%dm", minutes);
        }

        return "1m";
    }

    public static void showErrorDialog(final Context context, int message) {
        String messageString = context.getString(message);
        showErrorDialog(context, messageString);
    }

    public static void showErrorDialog(final Context context, String message) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.button_error)
                .setMessage(message)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
}