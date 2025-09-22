/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package id.co.bintangindokarya.mes.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for MES_ProdOutput
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="MES_ProdOutput")
public class X_MES_ProdOutput extends PO implements I_MES_ProdOutput, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250912L;

    /** Standard Constructor */
    public X_MES_ProdOutput (Properties ctx, int MES_ProdOutput_ID, String trxName)
    {
      super (ctx, MES_ProdOutput_ID, trxName);
      /** if (MES_ProdOutput_ID == 0)
        {
			setArticle (null);
			setDocAction (null);
// CO
			setDocStatus (null);
// DR
			setDocumentNo (null);
			setMES_ProdOutput_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setProcessed (false);
// N
			setProcessing (false);
// N
			setSAP_WorkCenter_ID (0);
			setSalesOrderNumber (null);
        } */
    }

    /** Standard Constructor */
    public X_MES_ProdOutput (Properties ctx, int MES_ProdOutput_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, MES_ProdOutput_ID, trxName, virtualColumns);
      /** if (MES_ProdOutput_ID == 0)
        {
			setArticle (null);
			setDocAction (null);
// CO
			setDocStatus (null);
// DR
			setDocumentNo (null);
			setMES_ProdOutput_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setProcessed (false);
// N
			setProcessing (false);
// N
			setSAP_WorkCenter_ID (0);
			setSalesOrderNumber (null);
        } */
    }

    /** Standard Constructor */
    public X_MES_ProdOutput (Properties ctx, String MES_ProdOutput_UU, String trxName)
    {
      super (ctx, MES_ProdOutput_UU, trxName);
      /** if (MES_ProdOutput_UU == null)
        {
			setArticle (null);
			setDocAction (null);
// CO
			setDocStatus (null);
// DR
			setDocumentNo (null);
			setMES_ProdOutput_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setProcessed (false);
// N
			setProcessing (false);
// N
			setSAP_WorkCenter_ID (0);
			setSalesOrderNumber (null);
        } */
    }

    /** Standard Constructor */
    public X_MES_ProdOutput (Properties ctx, String MES_ProdOutput_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, MES_ProdOutput_UU, trxName, virtualColumns);
      /** if (MES_ProdOutput_UU == null)
        {
			setArticle (null);
			setDocAction (null);
// CO
			setDocStatus (null);
// DR
			setDocumentNo (null);
			setMES_ProdOutput_ID (0);
			setMasterProductionOrder (null);
			setOrderNumber (null);
			setProcessed (false);
// N
			setProcessing (false);
// N
			setSAP_WorkCenter_ID (0);
			setSalesOrderNumber (null);
        } */
    }

    /** Load Constructor */
    public X_MES_ProdOutput (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_MES_ProdOutput[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Article.
		@param Article Article
	*/
	public void setArticle (String Article)
	{
		set_Value (COLUMNNAME_Article, Article);
	}

	/** Get Article.
		@return Article	  */
	public String getArticle()
	{
		return (String)get_Value(COLUMNNAME_Article);
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** &lt;None&gt; = -- */
	public static final String DOCACTION_None = "--";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Set Document Action.
		@param DocAction The targeted status of the document
	*/
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction()
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Set Document Status.
		@param DocStatus The current status of the document
	*/
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus()
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo Document sequence number of the document
	*/
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo()
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Kanban Number.
		@param KanbanNumber Kanban Number
	*/
	public void setKanbanNumber (String KanbanNumber)
	{
		set_Value (COLUMNNAME_KanbanNumber, KanbanNumber);
	}

	/** Get Kanban Number.
		@return Kanban Number	  */
	public String getKanbanNumber()
	{
		return (String)get_Value(COLUMNNAME_KanbanNumber);
	}

	/** Set Line Aggregator.
		@param LineAggregator Line Aggregator
	*/
	public void setLineAggregator (String LineAggregator)
	{
		set_Value (COLUMNNAME_LineAggregator, LineAggregator);
	}

	/** Get Line Aggregator.
		@return Line Aggregator	  */
	public String getLineAggregator()
	{
		return (String)get_Value(COLUMNNAME_LineAggregator);
	}

	/** Set MES_ProdOutput.
		@param MES_ProdOutput_ID MES_ProdOutput
	*/
	public void setMES_ProdOutput_ID (int MES_ProdOutput_ID)
	{
		if (MES_ProdOutput_ID < 1)
			set_ValueNoCheck (COLUMNNAME_MES_ProdOutput_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_MES_ProdOutput_ID, Integer.valueOf(MES_ProdOutput_ID));
	}

	/** Get MES_ProdOutput.
		@return MES_ProdOutput	  */
	public int getMES_ProdOutput_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MES_ProdOutput_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MES_ProdOutput_UU.
		@param MES_ProdOutput_UU MES_ProdOutput_UU
	*/
	public void setMES_ProdOutput_UU (String MES_ProdOutput_UU)
	{
		set_Value (COLUMNNAME_MES_ProdOutput_UU, MES_ProdOutput_UU);
	}

	/** Get MES_ProdOutput_UU.
		@return MES_ProdOutput_UU	  */
	public String getMES_ProdOutput_UU()
	{
		return (String)get_Value(COLUMNNAME_MES_ProdOutput_UU);
	}

	/** Scan Assembly = MSA */
	public static final String MES_TRANS_ID_ScanAssembly = "MSA";
	/** Scan Output = MSO */
	public static final String MES_TRANS_ID_ScanOutput = "MSO";
	/** Scan Sewing = MSS */
	public static final String MES_TRANS_ID_ScanSewing = "MSS";
	/** Scan Transfer = MST */
	public static final String MES_TRANS_ID_ScanTransfer = "MST";
	/** Set MES_Trans_ID.
		@param MES_Trans_ID MES_Trans_ID
	*/
	public void setMES_Trans_ID (String MES_Trans_ID)
	{

		set_Value (COLUMNNAME_MES_Trans_ID, MES_Trans_ID);
	}

	/** Get MES_Trans_ID.
		@return MES_Trans_ID	  */
	public String getMES_Trans_ID()
	{
		return (String)get_Value(COLUMNNAME_MES_Trans_ID);
	}

	/** Set Master Production Order.
		@param MasterProductionOrder Master Production Order
	*/
	public void setMasterProductionOrder (String MasterProductionOrder)
	{
		set_Value (COLUMNNAME_MasterProductionOrder, MasterProductionOrder);
	}

	/** Get Master Production Order.
		@return Master Production Order	  */
	public String getMasterProductionOrder()
	{
		return (String)get_Value(COLUMNNAME_MasterProductionOrder);
	}

	/** Set Movement Type.
		@param MovementType Method of moving the inventory
	*/
	public void setMovementType (String MovementType)
	{
		set_Value (COLUMNNAME_MovementType, MovementType);
	}

	/** Get Movement Type.
		@return Method of moving the inventory
	  */
	public String getMovementType()
	{
		return (String)get_Value(COLUMNNAME_MovementType);
	}

	/** Set Order Number.
		@param OrderNumber Order Number
	*/
	public void setOrderNumber (String OrderNumber)
	{
		set_ValueNoCheck (COLUMNNAME_OrderNumber, OrderNumber);
	}

	/** Get Order Number.
		@return Order Number	  */
	public String getOrderNumber()
	{
		return (String)get_Value(COLUMNNAME_OrderNumber);
	}

	/** Set Order Reference.
		@param POReference Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	*/
	public void setPOReference (String POReference)
	{
		set_ValueNoCheck (COLUMNNAME_POReference, POReference);
	}

	/** Get Order Reference.
		@return Transaction Reference Number (Sales Order, Purchase Order) of your Business Partner
	  */
	public String getPOReference()
	{
		return (String)get_Value(COLUMNNAME_POReference);
	}

	/** Set Processed.
		@param Processed The document has been processed
	*/
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed()
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed On.
		@param ProcessedOn The date+time (expressed in decimal format) when the document has been processed
	*/
	public void setProcessedOn (BigDecimal ProcessedOn)
	{
		set_Value (COLUMNNAME_ProcessedOn, ProcessedOn);
	}

	/** Get Processed On.
		@return The date+time (expressed in decimal format) when the document has been processed
	  */
	public BigDecimal getProcessedOn()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ProcessedOn);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Process Now.
		@param Processing Process Now
	*/
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing()
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set SAP_WorkCenter.
		@param SAP_WorkCenter_ID SAP_WorkCenter
	*/
	public void setSAP_WorkCenter_ID (int SAP_WorkCenter_ID)
	{
		if (SAP_WorkCenter_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SAP_WorkCenter_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SAP_WorkCenter_ID, Integer.valueOf(SAP_WorkCenter_ID));
	}

	/** Get SAP_WorkCenter.
		@return SAP_WorkCenter	  */
	public int getSAP_WorkCenter_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SAP_WorkCenter_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sales Order Number.
		@param SalesOrderNumber Sales Order Number
	*/
	public void setSalesOrderNumber (String SalesOrderNumber)
	{
		set_Value (COLUMNNAME_SalesOrderNumber, SalesOrderNumber);
	}

	/** Get Sales Order Number.
		@return Sales Order Number	  */
	public String getSalesOrderNumber()
	{
		return (String)get_Value(COLUMNNAME_SalesOrderNumber);
	}
}