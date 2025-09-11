package id.co.bintangindokarya.mes.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MMES_ProdOutputDetail extends X_MES_ProdOutputDetail {

	private static final long serialVersionUID = 4951034960545200681L;

	public MMES_ProdOutputDetail(Properties ctx, int MES_ProdOutputDetail_ID, String trxName) {
		super(ctx, MES_ProdOutputDetail_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_ProdOutputDetail(Properties ctx, int MES_ProdOutputDetail_ID, String trxName, String[] virtualColumns) {
		super(ctx, MES_ProdOutputDetail_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	public MMES_ProdOutputDetail(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_ProdOutputDetail(Properties ctx, String MES_ProdOutputDetail_UU, String trxName) {
		super(ctx, MES_ProdOutputDetail_UU, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_ProdOutputDetail(Properties ctx, String MES_ProdOutputDetail_UU, String trxName,
			String[] virtualColumns) {
		super(ctx, MES_ProdOutputDetail_UU, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

}
