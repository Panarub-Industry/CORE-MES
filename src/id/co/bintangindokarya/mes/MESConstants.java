package id.co.bintangindokarya.mes;

public class MESConstants {

	// ====== MOVEMENT TYPES (SAP Standard) ======
	public static final class MovementType {
	    // Goods Receipt
	    public static final String GOODS_RECEIPT = "101";   
	    public static final String GOODS_RECEIPT_REVERSAL = "102"; 
	    public static final String GOODS_RECEIPT_BLOCKED = "103";    
	    public static final String GOODS_RECEIPT_TO_BLOCKED_REV = "104";
	    public static final String GOODS_RECEIPT_FROM_BLOCKED = "105";
	    public static final String GOODS_RECEIPT_TO_UNRESTRICTED = "106";
	    public static final String GOODS_RECEIPT_TO_VALUE_BLOCKED = "107";
	    
	    // Goods Issue (Series 2xx - typical SAP pattern)
	    public static final String GOODS_ISSUE = "201";
	    public static final String GOODS_ISSUE_REVERSAL = "202"; 
	    
	    // Transfer/Movement (Series 3xx)
	    public static final String TRANSFER_POSTING = "301";
	    public static final String TRANSFER_UNRESTRICTED_TO_BLOCKED = "302";
	    public static final String TRANSFER_BLOCKED_TO_UNRESTRICTED = "303";
	    
	    // Production (Series 5xx)
	    public static final String PRODUCTION_RECEIPT = "501";
	    public static final String PRODUCTION_ISSUE = "502"; 
	    
	    // Custom MES Types (Series 9xx untuk custom)
	    public static final String MES_ASSEMBLY = "901";
	    public static final String MES_QUALITY_CHECK = "902";
	    public static final String MES_PACKAGING = "903";
	}
	
	// ====== MES TRANSACTION TYPES ======
    public static final class TransactionType {
        public static final String SCAN_ASSEMBLY = "MSA";
        public static final String SCAN_SEWING = "MSS";
    }
    
    // ====== BARCODE TYPES ======
    public static final class BarcodeType {
        public static final String BARCODE_UPC = "B1";
        public static final String BARCODE_SEWING = "B2";
        public static final String BARCODE_MES = "B3";
        public static final String BARCODE_SUBCONT = "B4";
        public static final String BARCODE_CUTTING = "B5";
    }
}
