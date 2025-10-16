package id.co.bintangindokarya.mes.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Trx;

import id.co.bintangindokarya.mes.MESConstants;

public class MMES_BarcodeDetail extends X_MES_BarcodeDetail {

	private static final long serialVersionUID = -256005561119940048L;

	public MMES_BarcodeDetail(Properties ctx, int MES_BarcodeDetail_ID, String trxName) {
		super(ctx, MES_BarcodeDetail_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_BarcodeDetail(Properties ctx, int MES_BarcodeDetail_ID, String trxName, String[] virtualColumns) {
		super(ctx, MES_BarcodeDetail_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	public MMES_BarcodeDetail(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_BarcodeDetail(Properties ctx, String MES_BarcodeDetail_UU, String trxName) {
		super(ctx, MES_BarcodeDetail_UU, trxName);
		// TODO Auto-generated constructor stub
	}

	public MMES_BarcodeDetail(Properties ctx, String MES_BarcodeDetail_UU, String trxName, String[] virtualColumns) {
		super(ctx, MES_BarcodeDetail_UU, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	/**
     * Cari barcode berdasarkan barcode string dan tipe barcode
     */
    public static MMES_BarcodeDetail findByBarcodeAndType(Properties ctx, String barcode, String barcodeTypeValue) {
    	try {
    		return new Query(ctx, Table_Name,
                    "mes_barcodedetail.isactive ='Y' "
                    + "AND mes_barcodedetail.AD_Org_ID=? "
                    + "AND mes_barcodedetail.AD_Client_ID=? "
                    + "AND MES_BarcodeType.value = ? "
                    + "AND mes_barcodedetail.barcode = ?", null)
                    .setClient_ID()
                    .setOnlyActiveRecords(true)
                    .addJoinClause("INNER JOIN MES_Barcode ON MES_Barcode.MES_Barcode_ID = mes_barcodedetail.MES_Barcode_ID")
                    .addJoinClause("INNER JOIN MES_BarcodeType ON MES_BarcodeType.MES_BarcodeType_ID = MES_Barcode.MES_BarcodeType_ID")
                    .setParameters(Env.getAD_Org_ID(ctx),Env.getAD_Client_ID(ctx),barcodeTypeValue, barcode)
                    .firstOnly();
    	} catch (Exception e) { 
    		e.printStackTrace();
    		return null;
    	}
        
    }
    
    private void saveProductionOutputDetail(Properties ctx, MV_MES_ProductionInfo stockDatas, String barcode, MMES_BarcodeDetail barcodeDatas) throws Exception {
    	int AD_User_ID = Env.getAD_User_ID(ctx);
    	String trxName = Trx.createTrxName("MSA_Detail");
        Trx trx = null;
        try {
            trx = Trx.get(trxName, true);
            trx.setDisplayName(getClass().getName() + "_addBarcodeItem");

            //MMES_ProdOutput prodOutput = MMES_ProdOutput.createOrGetDraft(ctx,MMES_ProdOutput.MES_TRANS_ID_ScanAssembly, 1000028);
            MMES_ProdOutput prodOutput = MMES_ProdOutput.getDraftForUser(ctx,Env.getAD_User_ID(ctx),MMES_ProdOutput.MES_TRANS_ID_ScanAssembly);

            MMES_ProdOutputDetail prodOutputDetail = new MMES_ProdOutputDetail(ctx, 0, trxName);
            prodOutputDetail.setMES_ProdOutput_ID(prodOutput.getMES_ProdOutput_ID());
            prodOutputDetail.setMovementType(MESConstants.MovementType.GOODS_RECEIPT);
            prodOutputDetail.setSAP_WorkCenter_ID(stockDatas.getSAP_WorkCenter_ID());
            prodOutputDetail.setBarcode(barcode);
            prodOutputDetail.setSalesOrderNumber(stockDatas.getSalesOrderNumber());
            prodOutputDetail.setPOReference(stockDatas.getPOReference());
            prodOutputDetail.setOrderNumber(stockDatas.getOrderNumber());
            prodOutputDetail.setMasterProductionOrder(stockDatas.getMasterProductionOrder());
            prodOutputDetail.setArticle(stockDatas.getProduct());
            prodOutputDetail.setSizeCustomer(barcodeDatas.getSizeCustomer());
            prodOutputDetail.setSizeFactory(stockDatas.getSizeFactory());
            prodOutputDetail.setQtyEntered(BigDecimal.ONE);
            prodOutputDetail.saveEx();

            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            throw new Exception("Gagal simpan detail output produksi", e);
        } finally {
            if (trx != null) trx.close();
        }
    }


}
