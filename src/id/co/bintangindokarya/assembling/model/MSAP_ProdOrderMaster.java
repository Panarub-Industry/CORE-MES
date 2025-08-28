package id.co.bintangindokarya.assembling.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MSAP_ProdOrderMaster extends X_SAP_ProdOrderMaster {

	private static final long serialVersionUID = 2340027869361695148L;

	public MSAP_ProdOrderMaster(Properties ctx, int SAP_ProdOrderMaster_ID, String trxName) {
		super(ctx, SAP_ProdOrderMaster_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSAP_ProdOrderMaster(Properties ctx, int SAP_ProdOrderMaster_ID, String trxName, String[] virtualColumns) {
		super(ctx, SAP_ProdOrderMaster_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	public MSAP_ProdOrderMaster(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSAP_ProdOrderMaster(Properties ctx, String SAP_ProdOrderMaster_UU, String trxName) {
		super(ctx, SAP_ProdOrderMaster_UU, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSAP_ProdOrderMaster(Properties ctx, String SAP_ProdOrderMaster_UU, String trxName,
			String[] virtualColumns) {
		super(ctx, SAP_ProdOrderMaster_UU, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	
}
