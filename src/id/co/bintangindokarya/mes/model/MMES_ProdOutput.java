package id.co.bintangindokarya.mes.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.process.DocAction;

public class MMES_ProdOutput extends X_MES_ProdOutput implements DocAction {

	private static final long serialVersionUID = 3241426180671628718L;

	public MMES_ProdOutput(Properties ctx, int MES_ProdOutput_ID, String trxName) {
		super(ctx, MES_ProdOutput_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_ProdOutput(Properties ctx, int MES_ProdOutput_ID, String trxName, String[] virtualColumns) {
		super(ctx, MES_ProdOutput_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	public MMES_ProdOutput(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_ProdOutput(Properties ctx, String MES_ProdOutput_UU, String trxName) {
		super(ctx, MES_ProdOutput_UU, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_ProdOutput(Properties ctx, String MES_ProdOutput_UU, String trxName, String[] virtualColumns) {
		super(ctx, MES_ProdOutput_UU, trxName, virtualColumns);
	}

	@Override
	public boolean processIt(String action) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unlockIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean invalidateIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String prepareIt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approveIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String completeIt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean voidIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closeIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reActivateIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocumentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getC_Currency_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
