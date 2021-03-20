/**
 * Created: 10.08.2018
 */
package de.freese.binding.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JList;
import de.freese.binding.collections.DefaultObservableList;
import de.freese.binding.collections.ObservableList;
import de.freese.binding.swing.list.DefaultObservableListListModel;

/**
 * @author Thomas Freese
 */
public class ListModelDemo
{
    /**
     * @param args String[]
     * @throws InterruptedException Falls was schief geht.
     */
    public static void main(final String[] args) throws InterruptedException
    {
        JFrame frame = new JFrame("Test-ListModel");
        frame.addWindowListener(new WindowAdapter()
        {
            /**
             * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
             */
            @Override
            public void windowClosing(final WindowEvent e)
            {
                System.exit(0);
            }
        });

        ObservableList<Map<Integer, String>> list = new DefaultObservableList<>(new ArrayList<>());
        list = list.sorted((o1, o2) -> o2.get(0).compareTo(o1.get(1))); // Absteigend nach erster Spalte.
        list = list.filtered(map -> (Integer.parseInt(map.get(0).split("[-]")[0]) % 2) == 0); // Nur jede 2. Zeile

        JList<Map<Integer, String>> jlist = new JList<>(new DefaultObservableListListModel<>(list));
        jlist.setVisibleRowCount(5);

        // frame.setLayout(new BorderLayout());
        // frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(jlist);

        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Add Rows
        for (int i = 0; i < 7; i++)
        {
            TimeUnit.MILLISECONDS.sleep(2000);

            Map<Integer, String> row = new HashMap<>();
            row.put(0, i + "-0");
            row.put(1, i + "-1");
            row.put(2, i + "-2");
            row.put(3, i + "-3");

            list.add(row);
        }

        // Delete Rows
        TimeUnit.MILLISECONDS.sleep(2000);
        list.remove(0);
        TimeUnit.MILLISECONDS.sleep(2000);
        list.remove(2);

        TimeUnit.MILLISECONDS.sleep(2000);
        list.clear();
    }
}
