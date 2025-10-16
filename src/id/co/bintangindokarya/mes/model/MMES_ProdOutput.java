package id.co.bintangindokarya.mes.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.compiere.util.Trx;

public class MMES_ProdOutput extends X_MES_ProdOutput implements DocAction {

	private static final long serialVersionUID = -8545516045803990055L;

	public MMES_ProdOutput(Properties ctx, int MES_ProdOutput_ID, String trxName) {
		super(ctx, MES_ProdOutput_ID, trxName);
	}

	public MMES_ProdOutput(Properties ctx, int MES_ProdOutput_ID, String trxName, String[] virtualColumns) {
		super(ctx, MES_ProdOutput_ID, trxName, virtualColumns);
	}

	public MMES_ProdOutput(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MMES_ProdOutput(Properties ctx, String MES_ProdOutput_UU, String trxName) {
		super(ctx, MES_ProdOutput_UU, trxName);
	}

	public MMES_ProdOutput(Properties ctx, String MES_ProdOutput_UU, String trxName, String[] virtualColumns) {
		super(ctx, MES_ProdOutput_UU, trxName, virtualColumns);
	}

	public static MMES_ProdOutput createOrGetDraftForAssembly(Properties ctx) {
        return createOrGetDraft(ctx, MMES_ProdOutput.MES_TRANS_ID_ScanAssembly);
    }
	
	public static MMES_ProdOutput getDraftForUser(Properties ctx, int userId, String transId) {
	    String whereClause = "DocStatus=? AND AD_Client_ID=? AND AD_Org_ID=? AND CreatedBy=? AND MES_Trans_ID=?";
	    return new Query(ctx, Table_Name, whereClause, null)
	            .setParameters(
	                DocAction.STATUS_Drafted,
	                Env.getAD_Client_ID(ctx),
	                Env.getAD_Org_ID(ctx),
	                userId,
	                transId
	            )
	            .setOrderBy("Created DESC")
	            .first();
	}

	
	/**
     * Create new draft or get existing draft MES_ProdOutput for current user
     * @param ctx Properties context
     * @param transId Transaction ID (e.g., "MSA" for ScanAssembly)
     * @return MMES_ProdOutput draft document No
     */
    public static MMES_ProdOutput createOrGetDraft(Properties ctx, String transId) {
        int AD_User_ID = Env.getAD_User_ID(ctx);
        
        String trxName = Trx.createTrxName(transId);
        Trx trx = Trx.get(trxName, true);
        trx.setDisplayName(MMES_ProdOutput.class.getName() + "_createOrGetDraft");

        try {
            // Try to find existing draft for current user
            MMES_ProdOutput existingDraft = getDraftForUser(ctx, AD_User_ID, transId);
            if (existingDraft != null && transId.equals(existingDraft.getMES_Trans_ID())) {
                trx.commit();
                return existingDraft;
            }

            // Create new draft document
            MMES_ProdOutput newDraft = new MMES_ProdOutput(ctx, 0, trxName);
            newDraft.setMES_Trans_ID(transId);
            newDraft.setDocStatus(DocAction.STATUS_Drafted);
            newDraft.saveEx();

            trx.commit();
            
            return newDraft;

        } catch (Exception e) {
            trx.rollback();
            throw new IllegalStateException("Failed to create or get draft for transId: " + transId, e);
        } finally {
            trx.close();
        }
    }
    


    @Override
    public void setDocStatus(String newStatus) {
        // WAJIB panggil parent method untuk set database field
    	
        super.setDocStatus(newStatus);
    }

    @Override
    public String getDocStatus() {
        // WAJIB panggil parent method untuk get database field
        return super.getDocStatus();
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

	@Override
	public String getDocAction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
