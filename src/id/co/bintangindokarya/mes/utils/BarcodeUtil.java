package id.co.bintangindokarya.mes.utils;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;


public class BarcodeUtil {

	/**
     * Simple data class untuk hasil query barcode detail
     */
    public static class BarcodeData {
        public String barcodeTypeValue;        // barcodetype.value
        public String barcodeTypeName;    // barcodetype.name
        public String barcode;            // barcodedetail.barcode
        public String sizeCustomer;       // barcodedetail.sizecustomer
        public String sizeFactory;        // barcodedetail.sizefactory
        public BigDecimal qtyBarcode;     // barcodedetail.qtybarcode
    }
    
    /**
     * Mengecek data barcode secara dinamis
     * @param ctx     Context dari iDempiere
     * @param filters Map filter (contoh: bt.value=B1, bd.barcode=4121300001036)
     * @return true jika barcode ditemukan, false jika tidak ada
     */
    public static boolean checkBarcode(Properties ctx, Map<String, Object> filters) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT 1 ")
        .append("FROM mes_barcode barcodeheader ")
        .append("INNER JOIN mes_barcodedetail barcodedetail ON barcodeheader.mes_barcode_id = barcodedetail.mes_barcode_id ")
        .append("INNER JOIN mes_barcodetype barcodetype ON barcodeheader.mes_barcodetype_id = barcodetype.mes_barcodetype_id ")
        .append("WHERE barcodedetail.isactive ='Y' ");

        List<Object> params = new ArrayList<>();

        // --- Tambahkan filter dinamis ---
        if (filters != null && !filters.isEmpty()) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                if (entry.getValue() != null) {
                    if (entry.getKey().startsWith("barcodetype.") || 
                        entry.getKey().startsWith("barcodedetail.") || 
                        entry.getKey().startsWith("barcodeheader.")) {
                        sql.append(" AND ").append(entry.getKey()).append(" = ?");
                        params.add(entry.getValue());
                    }
                }
            }
        }

        Integer result = DB.getSQLValueEx(null, sql.toString(), params.toArray());
        return result != null && result > 0;
    }
    
    /**
     * Get barcode data dengan detail lengkap (single record)
     * 
     * @param ctx Properties context
     * @param filters Map filter dengan format alias.column = value
     * @return BarcodeData object atau null jika tidak ditemukan
     */
    public static BarcodeData getBarcodeData(Properties ctx, Map<String, Object> filters) {
        StringBuilder sql = new StringBuilder();
        
        sql.append("SELECT barcodetype.value as barcodetypevalue, ")
           .append("       barcodetype.name as barcodetypename, ")
           .append("       barcodedetail.barcode, ")
           .append("       barcodedetail.sizecustomer, ")
           .append("       barcodedetail.sizefactory, ")
           .append("       barcodedetail.qtybarcode ")
           .append("FROM mes_barcode barcodeheader ")
           .append("INNER JOIN mes_barcodedetail barcodedetail ON barcodeheader.mes_barcode_id = barcodedetail.mes_barcode_id ")
           .append("INNER JOIN mes_barcodetype barcodetype ON barcodeheader.mes_barcodetype_id = barcodetype.mes_barcodetype_id ")
           .append("WHERE barcodedetail.isactive = 'Y'");

        List<Object> params = new ArrayList<>();

     // --- Tambahkan filter dinamis ---
        if (filters != null && !filters.isEmpty()) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                if (entry.getValue() != null) {
                    if (entry.getKey().startsWith("barcodetype.") || 
                        entry.getKey().startsWith("barcodedetail.") || 
                        entry.getKey().startsWith("barcodeheader.")) {
                        sql.append(" AND ").append(entry.getKey()).append(" = ?");
                        params.add(entry.getValue());
                    }
                }
            }
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql.toString(), null);
            DB.setParameters(pstmt, params.toArray());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                BarcodeData barcodeData = new BarcodeData();
                barcodeData.barcodeTypeValue = rs.getString("barcodetypevalue");
                barcodeData.barcodeTypeName = rs.getString("barcodetypename");
                barcodeData.barcode = rs.getString("barcode");
                barcodeData.sizeCustomer = rs.getString("sizecustomer");
                barcodeData.sizeFactory = rs.getString("sizefactory");
                barcodeData.qtyBarcode = rs.getBigDecimal("qtybarcode");

                return barcodeData;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DBException(e, sql.toString());
        } finally {
            DB.close(rs, pstmt);
        }
    }

}
