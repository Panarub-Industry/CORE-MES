package id.co.bintangindokarya.assembling.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MMes_BarcodeDetail extends X_MES_BarcodeDetail {

	private static final long serialVersionUID = -256005561119940048L;

	public MMes_BarcodeDetail(Properties ctx, int MES_BarcodeDetail_ID, String trxName) {
		super(ctx, MES_BarcodeDetail_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMes_BarcodeDetail(Properties ctx, int MES_BarcodeDetail_ID, String trxName, String[] virtualColumns) {
		super(ctx, MES_BarcodeDetail_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	public MMes_BarcodeDetail(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMes_BarcodeDetail(Properties ctx, String MES_BarcodeDetail_UU, String trxName) {
		super(ctx, MES_BarcodeDetail_UU, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMes_BarcodeDetail(Properties ctx, String MES_BarcodeDetail_UU, String trxName, String[] virtualColumns) {
		super(ctx, MES_BarcodeDetail_UU, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	

}
