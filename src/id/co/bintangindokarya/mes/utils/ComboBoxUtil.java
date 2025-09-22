package id.co.bintangindokarya.mes.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.adempiere.webui.component.Combobox;

/**
 * Utility class untuk mengisi Combobox dinamis di iDempiere MES (WebUI).
 * WAJIB menggunakan org.adempiere.webui.component.Combobox (bukan ZK asli).
 */
public class ComboBoxUtil {

    private static final Logger log = Logger.getLogger(ComboBoxUtil.class.getName());

    /**
     * Fill Combobox dari tabel standar (otomatis filter tenant & org)
     *
     * @param ctx           context (Env.getCtx())
     * @param tableName     nama tabel, contoh: "mes_machine"
     * @param keyColumn     kolom key (ID), contoh: "mes_machine_id"
     * @param displayColumn kolom yang ditampilkan, contoh: "name"
     * @return Combobox terisi data
     */
    public static Combobox fillComboFromTable(Properties ctx, String tableName,
                                              String keyColumn, String displayColumn) {
        Combobox combo = new Combobox();
        combo.setReadonly(true);

        int AD_Client_ID = Env.getAD_Client_ID(ctx);
        int AD_Org_ID = Env.getAD_Org_ID(ctx);

        String sql = "SELECT " + keyColumn + ", " + displayColumn
                + " FROM " + tableName
                + " WHERE ad_client_id=" + AD_Client_ID
                + " AND (ad_org_id=0 OR ad_org_id=" + AD_Org_ID + ")"
                + " AND isactive='Y'"
                + " ORDER BY " + displayColumn;

        try {
            KeyNamePair[] list = DB.getKeyNamePairs(sql, true);
            for (KeyNamePair knp : list) {
                combo.appendItem(knp.getName(), knp.getKey()); // langsung pakai appendItem
            }

            if (combo.getItemCount() > 0) {
                combo.setSelectedIndex(0);
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, "SQL Error in fillComboFromTable: " + e.getMessage() + " | SQL=" + sql, e);
            combo.appendItem("<< Error loading " + tableName + " >>", null);
            combo.setSelectedIndex(0);
        }

        return combo;
    }

    /**
     * Fill Combobox dari SQL bebas (wajib return 2 kolom: ID, Name)
     *
     * @param sql query lengkap
     * @return Combobox terisi data
     */
    public static Combobox fillComboFromSql(String sql) {
        Combobox combo = new Combobox();
        combo.setReadonly(true);

        try {
            KeyNamePair[] list = DB.getKeyNamePairs(sql, true);
            for (KeyNamePair knp : list) {
                combo.appendItem(knp.getName(), knp.getKey());
            }

            if (combo.getItemCount() > 0) {
                combo.setSelectedIndex(0);
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, "SQL Error in fillComboFromSql: " + e.getMessage() + " | SQL=" + sql, e);
            combo.appendItem("<< Error loading data >>", null);
            combo.setSelectedIndex(0);
        }

        return combo;
    }

    /**
     * Helper untuk ambil ID dari Combobox
     *
     * @param combo combobox
     * @return Integer ID (bisa null)
     */
    public static Integer getSelectedId(Combobox combo) {
        if (combo.getSelectedItem() == null) return null;
        return (Integer) combo.getSelectedItem().getValue();
    }

    /**
     * Helper untuk ambil Label dari Combobox
     *
     * @param combo combobox
     * @return String label (bisa null)
     */
    public static String getSelectedLabel(Combobox combo) {
        if (combo.getSelectedItem() == null) return null;
        return combo.getSelectedItem().getLabel();
    }
}
