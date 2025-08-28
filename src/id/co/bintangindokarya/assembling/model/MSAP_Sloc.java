package id.co.bintangindokarya.assembling.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MSAP_Sloc extends X_SAP_Sloc {

	private static final long serialVersionUID = 2988802294182298606L;

	public MSAP_Sloc(Properties ctx, int SAP_Sloc_ID, String trxName) {
		super(ctx, SAP_Sloc_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSAP_Sloc(Properties ctx, int SAP_Sloc_ID, String trxName, String[] virtualColumns) {
		super(ctx, SAP_Sloc_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	public MSAP_Sloc(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSAP_Sloc(Properties ctx, String SAP_Sloc_UU, String trxName) {
		super(ctx, SAP_Sloc_UU, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSAP_Sloc(Properties ctx, String SAP_Sloc_UU, String trxName, String[] virtualColumns) {
		super(ctx, SAP_Sloc_UU, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}
	
}
