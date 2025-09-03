package id.co.bintangindokarya.mes.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MMES_Barcode extends X_MES_Barcode {

	private static final long serialVersionUID = 2209686668744848112L;

	public MMES_Barcode(Properties ctx, int MES_Barcode_ID, String trxName) {
		super(ctx, MES_Barcode_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_Barcode(Properties ctx, int MES_Barcode_ID, String trxName, String[] virtualColumns) {
		super(ctx, MES_Barcode_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	public MMES_Barcode(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_Barcode(Properties ctx, String MES_Barcode_UU, String trxName) {
		super(ctx, MES_Barcode_UU, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_Barcode(Properties ctx, String MES_Barcode_UU, String trxName, String[] virtualColumns) {
		super(ctx, MES_Barcode_UU, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

}
