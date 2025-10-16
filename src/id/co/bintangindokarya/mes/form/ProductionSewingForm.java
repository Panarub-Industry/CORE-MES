package id.co.bintangindokarya.mes.form;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.IFormController;
import org.compiere.acct.Doc;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Center;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
//import org.zkoss.zul.Label;
// Removed conflicting ZKoss imports - using fully qualified names instead
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import id.co.bintangindokarya.erp.model.MSAP_WorkCenter;
import id.co.bintangindokarya.mes.MESConstants;
import id.co.bintangindokarya.mes.form.StyleManager;
import id.co.bintangindokarya.mes.model.MMES_BarcodeDetail;
import id.co.bintangindokarya.mes.model.MMES_ProdOutput;
import id.co.bintangindokarya.mes.model.MMES_ProdOutputDetail;
import id.co.bintangindokarya.mes.model.MV_MES_ProductionInfo;
import id.co.bintangindokarya.mes.utils.QRCodeUtil;



public class ProductionSewingForm extends ADForm implements IFormController {

    private static final long serialVersionUID = 412332251652633311L;
    
    private static final CLogger log = CLogger.getCLogger(ProductionSewingForm.class);

    private Desktop desktop;
    
    // UI Components
    private Label titleLabel;
    private Label dateTimeLabel;
    private Textbox tenantField;
    private Textbox organizationField;
    private Textbox documentNoField;
    private Combobox planLineCombo;
    private Combobox poCombo;
    private Textbox barcodeField;
    private Button scanButton;
    private Div messagePanel;
    private Div formPanel;
    private Div infoPanel;
    private Listbox itemListbox;
    private Label scanPerHourLabel;
    private Label totalScanLabel;
    private Button detailButton;
    private Button submitButton;
    private Label helpLabel;
    private Label statusLabel;
    private Div statsPanel;
    private Div barcodeContainer;


    @Override
    protected void initForm() {
        try {
            desktop = Executions.getCurrent().getDesktop();
            createSewingLayout();
            initializeComponents();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to initialize form", e);
        }
    }
    
    private void initializeComponents() {
        updateDateTime();
        this.onLoadTableList();
        // Add event listeners here if needed
    }


    /* =====================================================
     * ================ UI LAYOUT CREATION =================
     * ===================================================== */
    private void createSewingLayout() {
        Borderlayout mainLayout = new Borderlayout();
        mainLayout.setStyle(StyleManager.combine(
                StyleManager.W_FULL,
                StyleManager.H_FULL,
                StyleManager.BG_GRAY_100,
                StyleManager.getMobileViewport()));
        mainLayout.setVflex("1");
        mainLayout.setHflex("1");
        this.appendChild(mainLayout);

        // North - Title Bar
        North north = new North();
        north.setStyle(StyleManager.combine(StyleManager.BG_WHITE, StyleManager.BORDER_BOTTOM_1));
        north.setSize("60px");

        Div titleBar = createTitleBar();
        north.appendChild(titleBar);
        mainLayout.appendChild(north);

        // Center - Main Content
        Center center = new Center();
        center.setStyle(StyleManager.combine(StyleManager.BG_WHITE, "padding: 12px 20px;"));
        center.setHflex("1");

        Vbox centerContent = new Vbox();
        centerContent.setStyle(StyleManager.combine(StyleManager.W_FULL, StyleManager.H_FULL));
        centerContent.setSpacing("8px");
        centerContent.setVflex("1");
        centerContent.setHflex("1");

        Div formInfoSection = createFormInfoSection();
        centerContent.appendChild(formInfoSection);

        Div tableSection = createTableSection();
        tableSection.setVflex("1");
        centerContent.appendChild(tableSection);

        center.appendChild(centerContent);
        mainLayout.appendChild(center);

        // South - Statistics
        South south = new South();
        south.setStyle(StyleManager.combine(StyleManager.BG_GRAY_50, StyleManager.BORDER_TOP_2));
        south.setSize("150px");

        createBottomPanel();
        south.appendChild(statsPanel);
        mainLayout.appendChild(south);
    }

    private Div createTitleBar() {
        Div titleBar = new Div();
        titleBar.setStyle(StyleManager.combine(
                StyleManager.FLEX,
                StyleManager.JUSTIFY_BETWEEN,
                StyleManager.ITEMS_CENTER,
                StyleManager.W_FULL,
                StyleManager.P_4));

        titleLabel = new Label("Sewing");
        titleLabel.setStyle(StyleManager.combine(
                StyleManager.TEXT_3XL,
                StyleManager.FONT_BOLD,
                "color: #374151;"));

        dateTimeLabel = new Label();
        dateTimeLabel.setStyle(StyleManager.combine(
                StyleManager.TEXT_BASE,
                "color: #6b7280;"));
        updateDateTime();

        titleBar.appendChild(titleLabel);
        titleBar.appendChild(dateTimeLabel);

        return titleBar;
    }

    private Div createFormInfoSection() {
        Div formInfoSection = new Div();
        formInfoSection.setStyle(StyleManager.combine(
                StyleManager.FLEX,
                StyleManager.GAP_4,
                StyleManager.W_FULL));

        Div leftColumn = new Div();
        leftColumn.setStyle(StyleManager.combine(
                "width: 500px;", // Fixed width to match form panel
                StyleManager.MIN_W_0));
        createFormInputs();
        leftColumn.appendChild(formPanel);

        Div rightColumn = new Div();
        rightColumn.setStyle(StyleManager.combine(
                "flex: 1;", // Take remaining width to match table section
                "min-width: 0;", // Allow shrinking
                "height: auto;")); // Match height with form inputs
        createInfoPanel();
        rightColumn.appendChild(infoPanel);

        formInfoSection.appendChild(leftColumn);
        formInfoSection.appendChild(rightColumn);

        return formInfoSection;
    }

    private void createFormInputs() {
        formPanel = new Div();
        formPanel.setStyle("width: 500px;"); // Reduced width to minimize space

        // Initialize fields first
        initializeFormFields();

        Div mainContainer = new Div();
        mainContainer.setStyle(StyleManager.combine(
                StyleManager.BG_WHITE,
                StyleManager.BORDER,
                StyleManager.ROUNDED_MD,
                StyleManager.P_4,
                StyleManager.SHADOW_SM));

        Div gridContainer = new Div();
        gridContainer.setStyle(StyleManager.getEnhancedFormGrid());

        // Row 1: Tenant | Organization
        gridContainer.appendChild(createFormFieldContainer("Tenant", tenantField));
        gridContainer.appendChild(createFormFieldContainer("Organization", organizationField));

        // Row 2: Plan Line | Document No
        gridContainer.appendChild(createFormFieldContainer("Plan Line", planLineCombo));
        gridContainer.appendChild(createFormFieldContainer("Document No", documentNoField));

        // Row 3: PO # | Scan Button
        gridContainer.appendChild(createFormFieldContainer("PO #", poCombo));
        gridContainer.appendChild(createScanButtonContainer());

        mainContainer.appendChild(gridContainer);
        formPanel.appendChild(mainContainer);
    }

    private Div createScanButtonContainer() {
        Div container = new Div();
        container.setStyle(StyleManager.combine(
                StyleManager.FLEX_COL,
                StyleManager.GAP_2,
                StyleManager.W_FULL,
                StyleManager.MIN_W_0,
                StyleManager.BOX_SIZING_BORDER));

        // Empty label to align with other fields
        Label label = new Label("\u00A0"); // Non-breaking space
        label.setStyle(StyleManager.combine(
                StyleManager.TEXT_SM,
                StyleManager.FONT_BOLD,
                "color: #374151;"));
        container.appendChild(label);

        // Scan Button with consistent width
        scanButton.setStyle("background: #10b981; color: white; border: none; padding: 8px 20px; border-radius: 4px; font-weight: 500; font-size: 14px; width: 100%; cursor: pointer;");
        container.appendChild(scanButton);

        return container;
    }

    private void initializeFormFields() {
        tenantField = new Textbox("PT Bintang Indokarya Gemilang");
        tenantField.setStyle(StyleManager.getInputEditable());
        tenantField.setHflex("1");
        tenantField.setReadonly(true);
        
        organizationField = new Textbox("Production");
        organizationField.setStyle(StyleManager.getInputEditable());
        organizationField.setHflex("1");
        organizationField.setReadonly(true);
        
        // Create or get draft document dan update document field
        documentNoField = new Textbox(onCreateOrGetDraft());
        documentNoField.setStyle(StyleManager.getInputEditable());
        documentNoField.setHflex("1");
        documentNoField.setReadonly(true);
        
        // Initialize Plan Line Combo
        planLineCombo = new Combobox();
        //planLineCombo.setStyle("height: 32px; padding: 6px 10px; border: 1px solid #d1d5db; border-radius: 4px; font-size: 14px;");
        planLineCombo = onLoadPlanLineComboBox(planLineCombo);
        planLineCombo.addEventListener(Events.ON_SELECT, event -> {
            Integer selectedValue = (Integer) planLineCombo.getSelectedItem().getValue();
            int selectedPlanLine = selectedValue.intValue();
            poCombo = onLoadPOSOComboBox(poCombo, selectedPlanLine);
        });

        // Initialize PO Combo
        poCombo = new Combobox();
        //poCombo.setStyle("height: 32px; padding: 6px 10px; border: 1px solid #d1d5db; border-radius: 4px; font-size: 14px;");
        if (planLineCombo.getItemCount() > 0) {
            Integer firstValue = (Integer) planLineCombo.getItemAtIndex(0).getValue();
            int firstPlanLine = firstValue.intValue();
            poCombo = onLoadPOSOComboBox(poCombo, firstPlanLine);
        }
        
        // Initialize scan button
        scanButton = new Button("Scan");
        scanButton.setStyle("background: #10b981; color: white; border: none; padding: 8px 20px; border-radius: 4px; font-weight: 500; font-size: 14px; width: 100%; cursor: pointer;");
        
        // Add event listener for scan button
        scanButton.addEventListener("onClick", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                showScanDialog();
            }
        });
    }

    private Div createFormFieldContainer(String labelText, Object field) {
        return createFormFieldContainer(labelText, field, null);
    }

    private Div createFormFieldContainer(String labelText, Object field, String width) {
        Div container = new Div();
        container.setStyle(StyleManager.combine(
                StyleManager.FLEX_COL,
                StyleManager.GAP_2,
                StyleManager.W_FULL,
                StyleManager.MIN_W_0,
                StyleManager.BOX_SIZING_BORDER));

        Label label = new Label(labelText + ":");
        label.setStyle(StyleManager.combine(
                StyleManager.TEXT_SM,
                StyleManager.FONT_BOLD,
                "color: #374151;"));
        container.appendChild(label);

        // Don't override the styling - field already has proper styling
        if (field instanceof Component) {
            container.appendChild((Component) field);
        }

        return container;
    }

    private void createInfoPanel() {
        infoPanel = new Div();
        infoPanel.setStyle(StyleManager.combine(
                StyleManager.BG_WHITE,
                StyleManager.BORDER,
                StyleManager.ROUNDED_MD,
                StyleManager.P_4,
                StyleManager.SHADOW_SM,
                StyleManager.W_FULL,
                "height: 100%;", // Match height with form inputs
                "display: flex;",
                "flex-direction: column;")); // Use flex column to distribute space

        Label infoTitle = new Label("Information Message");
        infoTitle.setStyle(StyleManager.combine(
                StyleManager.TEXT_SM,
                StyleManager.FONT_BOLD,
                "color: #374151;",
                StyleManager.MB_3));

     // ...existing code...

        messagePanel = new Div();
        messagePanel.setStyle(StyleManager.combine(
                "background: transparent;", // Ubah dari #ea580c ke transparent
                "color: #6b7280;", // Ubah dari white ke abu-abu
                "padding: 8px 14px;", // Kurangi padding
                "border-radius: 4px;",
                "font-size: 14px;",
                "font-weight: normal;", // Ubah dari 600 ke normal
                "text-align: left;", // Tetap left
                "flex: 1;",
                "display: flex;",
                "align-items: flex-start;", // Ubah dari center ke flex-start (atas)
                "justify-content: flex-start;", // Tetap flex-start (kiri)
                StyleManager.W_FULL));
        messagePanel.appendChild(new Label("Please select PO !"));

        // ...existing code...

        infoPanel.appendChild(infoTitle);
        infoPanel.appendChild(messagePanel);
    }

    private Div createTableSection() {
        Div tableSection = new Div();
        tableSection.setStyle(StyleManager.combine(
                StyleManager.BG_WHITE,
                StyleManager.BORDER,
                StyleManager.ROUNDED_MD,
                StyleManager.P_4,
                StyleManager.SHADOW_SM,
                StyleManager.W_FULL));
        tableSection.setVflex("1");

        // Barcode input section
        Div barcodeSection = new Div();
        barcodeSection.setStyle(StyleManager.combine(
                StyleManager.FLEX,
                StyleManager.ITEMS_CENTER,
                StyleManager.GAP_4,
                StyleManager.MB_4));

//        Label barcodeLabel = new Label("Barcode:");
//        barcodeLabel.setStyle(StyleManager.combine(
//                StyleManager.TEXT_SM,
//                StyleManager.FONT_BOLD,
//                "color: #374151;",
//                "min-width: 65px;"));
//
//        barcodeField = new Textbox();
//        barcodeField.setStyle(StyleManager.combine(
//                "border: 1px solid #d1d5db;",
//                "padding: 6px 10px;",
//                "font-size: 14px;",
//                "border-radius: 4px;",
//                "flex: 1;",
//                "max-width: 300px;"));
//        barcodeField.setPlaceholder("Scan or enter barcode");
//
//        barcodeSection.appendChild(barcodeLabel);
//        barcodeSection.appendChild(barcodeField);
//        tableSection.appendChild(barcodeSection);

        // Data Grid with improved styling matching screenshot
        itemListbox = new Listbox();
        itemListbox.setStyle(StyleManager.combine(
                StyleManager.W_FULL,
                "height: 280px;", // Increased height to show 10 records clearly
                StyleManager.BORDER,
                StyleManager.ROUNDED,
                "font-size: 12px;"));
        itemListbox.appendChild(new org.zkoss.zul.Listhead());
        
        org.zkoss.zul.Listhead listhead = (org.zkoss.zul.Listhead) itemListbox.getListhead();
        listhead.setStyle(StyleManager.combine(
                StyleManager.BG_GRAY_50,
                StyleManager.BORDER_BOTTOM_1,
                "height: 28px;"));
        
        // Column headers with exact proportions from screenshot
        org.zkoss.zul.Listheader noHeader = new org.zkoss.zul.Listheader("No", null, "35px");
        noHeader.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                StyleManager.FONT_BOLD,
                "color: #475569;",
                StyleManager.TEXT_XS,
                "padding: 3px;"));
        
        org.zkoss.zul.Listheader barcodeHeader = new org.zkoss.zul.Listheader("Barcode UPC", null, "140px");
        barcodeHeader.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                StyleManager.FONT_BOLD,
                "color: #475569;",
                StyleManager.TEXT_XS,
                "padding: 3px;"));
        
        org.zkoss.zul.Listheader poHeader = new org.zkoss.zul.Listheader("PO", null, "90px");
        poHeader.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                StyleManager.FONT_BOLD,
                "color: #475569;",
                StyleManager.TEXT_XS,
                "padding: 3px;"));
        
        org.zkoss.zul.Listheader articleHeader = new org.zkoss.zul.Listheader("Article", null, "70px");
        articleHeader.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                StyleManager.FONT_BOLD,
                "color: #475569;",
                StyleManager.TEXT_XS,
                "padding: 3px;"));
        
        org.zkoss.zul.Listheader sizeHeader = new org.zkoss.zul.Listheader("Size", null, "50px");
        sizeHeader.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                StyleManager.FONT_BOLD,
                "color: #475569;",
                StyleManager.TEXT_XS,
                "padding: 3px;"));
        
        org.zkoss.zul.Listheader qtyHeader = new org.zkoss.zul.Listheader("Qty", null, "45px");
        qtyHeader.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                StyleManager.FONT_BOLD,
                "color: #475569;",
                StyleManager.TEXT_XS,
                "padding: 3px;"));
        
        org.zkoss.zul.Listheader actionHeader = new org.zkoss.zul.Listheader("Action", null, "55px");
        actionHeader.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                StyleManager.FONT_BOLD,
                "color: #475569;",
                StyleManager.TEXT_XS,
                "padding: 3px;"));
        
        listhead.appendChild(noHeader);
        listhead.appendChild(barcodeHeader);
        listhead.appendChild(poHeader);
        listhead.appendChild(articleHeader);
        listhead.appendChild(sizeHeader);
        listhead.appendChild(qtyHeader);
        listhead.appendChild(actionHeader);

        // Add table footer with total
        org.zkoss.zul.Listfoot listfoot = new org.zkoss.zul.Listfoot();
        listfoot.setStyle(StyleManager.combine(
                StyleManager.BG_GRAY_50,
                "border-top: 1px solid #e5e7eb;",
                "height: 32px;"));
        
        // Footer cells - "Total" in Size column, "4" in Qty column
        org.zkoss.zul.Listfooter emptyFooter1 = new org.zkoss.zul.Listfooter(""); // No
        org.zkoss.zul.Listfooter emptyFooter2 = new org.zkoss.zul.Listfooter(""); // Barcode UPC
        org.zkoss.zul.Listfooter emptyFooter3 = new org.zkoss.zul.Listfooter(""); // PO
        org.zkoss.zul.Listfooter emptyFooter4 = new org.zkoss.zul.Listfooter(""); // Article
        org.zkoss.zul.Listfooter sizeFooter = new org.zkoss.zul.Listfooter("Total"); // Size column
        sizeFooter.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                StyleManager.FONT_BOLD,
                "color: #374151;",
                StyleManager.TEXT_SM));
        org.zkoss.zul.Listfooter qtyFooter = new org.zkoss.zul.Listfooter("4"); // Qty column
        qtyFooter.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                StyleManager.FONT_BOLD,
                "color: #374151;",
                StyleManager.TEXT_SM));
        org.zkoss.zul.Listfooter emptyFooter7 = new org.zkoss.zul.Listfooter(""); // Action
        
        listfoot.appendChild(emptyFooter1);
        listfoot.appendChild(emptyFooter2);
        listfoot.appendChild(emptyFooter3);
        listfoot.appendChild(emptyFooter4);
        listfoot.appendChild(sizeFooter);
        listfoot.appendChild(qtyFooter);
        listfoot.appendChild(emptyFooter7);
        
        itemListbox.appendChild(listfoot);

        // Add sample data
        //this.onLoadTableList();
        

        // Bottom section with total and buttons
        Div bottomBox = new Div();
        bottomBox.setStyle(StyleManager.combine(
                StyleManager.FLEX,
                StyleManager.JUSTIFY_BETWEEN,
                StyleManager.ITEMS_CENTER,
                StyleManager.W_FULL,
                "margin-top: 6px; padding-top: 6px;",
                StyleManager.BORDER_TOP_2,
                "height: 32px;"));

        helpLabel = new Label("(F1) Help");
        helpLabel.setStyle(StyleManager.combine(
                "color: #6b7280;",
                StyleManager.TEXT_XS,
                StyleManager.FONT_BOLD)); // Changed to bold

        Div rightBottomBox = new Div();
        rightBottomBox.setStyle(StyleManager.combine(
                StyleManager.FLEX,
                StyleManager.ITEMS_CENTER,
                StyleManager.GAP_3));

        // Status and Submit section only (Total moved to table footer)
//        statusLabel = new Label("Draft");
//        setStatusInfo("Draft");
        statusLabel = new Label("Draft");
        statusLabel.setStyle(StyleManager.combine(
                "color: #f59e0b;",
                StyleManager.FONT_BOLD,
                StyleManager.TEXT_SM,
                "background: #fef3c7;",
                "padding: 2px 6px;",
                StyleManager.ROUNDED_SM));

        submitButton = new Button("Submit");
        submitButton.setStyle(StyleManager.combine(
                "background: #3b82f6;",
                "color: white;",
                "border: none;",
                "padding: 6px 16px;",
                StyleManager.ROUNDED,
                StyleManager.FONT_BOLD,
                StyleManager.TEXT_SM,
                StyleManager.CURSOR_POINTER));
        submitButton.addEventListener("onClick", event -> onSubmit());

        rightBottomBox.appendChild(statusLabel);
        rightBottomBox.appendChild(submitButton);

        bottomBox.appendChild(helpLabel);
        bottomBox.appendChild(rightBottomBox);

        tableSection.appendChild(itemListbox);
        tableSection.appendChild(bottomBox);

        return tableSection;
    }

    private void createBottomPanel() {
        statsPanel = new Div();
        statsPanel.setStyle(StyleManager.combine(
                StyleManager.W_FULL,
                StyleManager.BG_GRAY_50,
                "padding: 10px 18px;",
                StyleManager.BORDER_TOP_2));

        Div statsBox = new Div();
        statsBox.setStyle(StyleManager.combine(
                StyleManager.FLEX,
                StyleManager.ITEMS_CENTER,
                StyleManager.JUSTIFY_CENTER,
                StyleManager.W_FULL,
                "gap: 18px;",
                "max-width: 750px;",
                "margin: 0 auto;"));

        // Scan Per Jam - compact styling to match screenshot
        Div scanPerHourBox = new Div();
        scanPerHourBox.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                "padding: 10px 18px;",
                StyleManager.BORDER,
                StyleManager.ROUNDED_MD,
                StyleManager.BG_WHITE,
                "flex: 1;",
                "max-width: 200px;",
                StyleManager.SHADOW_SM));
        Label scanPerHourTitle = new Label("Scan Per Jam");
        scanPerHourTitle.setStyle(StyleManager.combine(
                StyleManager.TEXT_XS,
                StyleManager.FONT_BOLD,
                "color: #64748b;",
                "margin-bottom: 5px;",
                StyleManager.DISPLAY_BLOCK));
        scanPerHourLabel = new Label("0");
        scanPerHourLabel.setStyle(StyleManager.combine(
                "font-size: 28px;",
                StyleManager.FONT_BOLD,
                "color: #1e293b;",
                StyleManager.LINE_HEIGHT_1,
                "margin: 0;"));
        scanPerHourBox.appendChild(scanPerHourTitle);
        scanPerHourBox.appendChild(scanPerHourLabel);

        // Total Scan - compact styling to match screenshot
        Div totalScanBox = new Div();
        totalScanBox.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                "padding: 10px 18px;",
                StyleManager.BORDER,
                StyleManager.ROUNDED_MD,
                StyleManager.BG_WHITE,
                "flex: 1;",
                "max-width: 200px;",
                StyleManager.SHADOW_SM));
        Label totalScanTitle = new Label("Total Scan");
        totalScanTitle.setStyle(StyleManager.combine(
                StyleManager.TEXT_XS,
                StyleManager.FONT_BOLD,
                "color: #64748b;",
                "margin-bottom: 5px;",
                StyleManager.DISPLAY_BLOCK));
        totalScanLabel = new Label("0");
        totalScanLabel.setStyle(StyleManager.combine(
                "font-size: 28px;",
                StyleManager.FONT_BOLD,
                "color: #1e293b;",
                StyleManager.LINE_HEIGHT_1,
                "margin: 0;"));
        totalScanBox.appendChild(totalScanTitle);
        totalScanBox.appendChild(totalScanLabel);

        // Report - compact styling to match screenshot
        Div reportBox = new Div();
        reportBox.setStyle(StyleManager.combine(
                StyleManager.TEXT_CENTER,
                "padding: 10px 18px;",
                StyleManager.BORDER,
                StyleManager.ROUNDED_MD,
                StyleManager.BG_WHITE,
                "flex: 1;",
                "max-width: 200px;",
                StyleManager.SHADOW_SM));
        Label reportTitle = new Label("Report");
        reportTitle.setStyle(StyleManager.combine(
                StyleManager.TEXT_XS,
                StyleManager.FONT_BOLD,
                "color: #64748b;",
                "margin-bottom: 7px;",
                StyleManager.DISPLAY_BLOCK));
        detailButton = new Button("Detail");
        detailButton.setStyle(StyleManager.combine(
                "background: #f59e0b;",
                "color: white;",
                "border: none;",
                "padding: 5px 14px;",
                StyleManager.ROUNDED,
                StyleManager.FONT_BOLD,
                StyleManager.TEXT_XS,
                StyleManager.CURSOR_POINTER,
                "width: 70%;"));
        detailButton.addEventListener(Events.ON_CLICK, event -> {
            this.showReportDetail();
        });

        reportBox.appendChild(reportTitle);
        reportBox.appendChild(detailButton);

        statsBox.appendChild(scanPerHourBox);
        statsBox.appendChild(totalScanBox);
        statsBox.appendChild(reportBox);

        statsPanel.appendChild(statsBox);
    }

    private void setStatusInfo(String status) {
        if (statusLabel != null) {
            statusLabel.setValue(status);
            
            // Set style based on status
            String style = "font-weight: bold; font-size: 12px; padding: 2px 6px; border-radius: 2px;";
            
            switch (status.toLowerCase()) {
                case "draft":
                    style += " color: #f59e0b; background: #fef3c7;";
                    break;
                case "completed":
                    style += " color: #059669; background: #d1fae5;";
                    break;
                case "processing":
                    style += " color: #3b82f6; background: #dbeafe;";
                    break;
                case "error":
                    style += " color: #ef4444; background: #fecaca;";
                    break;
                default:
                    style += " color: #6b7280; background: #f3f4f6;";
            }
            
            statusLabel.setStyle(style);
        }
    }
    
    private void clearTableData() {
        if (itemListbox != null) {
            // Remove all items except header and footer
            List<Component> toRemove = new ArrayList<>();
            for (Component child : itemListbox.getChildren()) {
                if (child instanceof org.zkoss.zul.Listitem) {
                    toRemove.add(child);
                }
            }
            for (Component item : toRemove) {
                item.detach();
            }
        }
    }

    private void addTableRow(String no, String barcode, String po, String article, String size, String qty) {
        org.zkoss.zul.Listitem item = new org.zkoss.zul.Listitem();
        item.appendChild(new org.zkoss.zul.Listcell(no));
        item.appendChild(new org.zkoss.zul.Listcell(barcode));
        item.appendChild(new org.zkoss.zul.Listcell(po));
        item.appendChild(new org.zkoss.zul.Listcell(article));
        item.appendChild(new org.zkoss.zul.Listcell(size));
        item.appendChild(new org.zkoss.zul.Listcell(qty));
        
        org.zkoss.zul.Listcell actionCell = new org.zkoss.zul.Listcell();
        Button deleteBtn = new Button("-");
        deleteBtn.setStyle(StyleManager.combine(
                "background: #ef4444;",
                "color: white;",
                "border: none;",
                "padding: 4px 8px;",
                StyleManager.ROUNDED_SM,
                StyleManager.TEXT_XS,
                "width: 25px; height: 25px;"));
        
        // Add delete functionality
        deleteBtn.addEventListener("onClick", event -> {
            try {
                onDeleteTableRow(item, barcode);
            } catch (Exception e) {
                log.log(Level.SEVERE, "Error deleting row", e);
            }
        });
        
        actionCell.appendChild(deleteBtn);
        item.appendChild(actionCell);
        itemListbox.appendChild(item);
    }
    
    private void refreshRowNumbers() {
        int rowNo = 1;
        for (Component child : itemListbox.getChildren()) {
            if (child instanceof org.zkoss.zul.Listitem listItem) {
                List<Component> cells = listItem.getChildren();
                if (!cells.isEmpty() && cells.get(0) instanceof org.zkoss.zul.Listcell firstCell) {
                    firstCell.setLabel(String.valueOf(rowNo++));
                }
            }
        }
        
        // Update footer total
        updateTableFooter(rowNo - 1);
    }

    private void updateTableFooter(int totalCount) {
        if (itemListbox != null && itemListbox.getListfoot() != null) {
            org.zkoss.zul.Listfoot listfoot = itemListbox.getListfoot();
            List<Component> footers = listfoot.getChildren();
            
            // Calculate total qty from all visible rows
            int totalQty = 0;
            for (Component child : itemListbox.getChildren()) {
                if (child instanceof org.zkoss.zul.Listitem listItem) {
                    List<Component> cells = listItem.getChildren();
                    if (cells.size() > 5 && cells.get(5) instanceof org.zkoss.zul.Listcell qtyCell) {
                        try {
                            totalQty += Integer.parseInt(qtyCell.getLabel());
                        } catch (NumberFormatException e) {
                            // Ignore invalid numbers
                        }
                    }
                }
            }
            
            // Update qty footer (index 5 = Qty column)
            if (footers.size() > 5 && footers.get(5) instanceof org.zkoss.zul.Listfooter qtyFooter) {
                qtyFooter.setLabel(String.valueOf(totalQty));
            }
        }
    } 
    
    private void updateDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        if (dateTimeLabel != null) {
            dateTimeLabel.setValue(sdf.format(new Date()));
        }
    }

    @Override
    public ADForm getForm() {
        return this;
    }
    
    // Method to show scan dialog based on mockup
    private void showScanDialog() {
        // Create a simple window dialog
        Window dialog = new Window();
        dialog.setTitle("Sewing");
        dialog.setWidth("900px");
        dialog.setHeight("750px"); // Increased height to accommodate all content
        dialog.setClosable(true);
        dialog.setStyle("padding: 20px;");
        
        // Main container - removed overflow from here
        Div mainContainer = new Div();
        mainContainer.setStyle("display: flex; flex-direction: column; gap: 20px; height: calc(100% - 40px);");
        
        // Top section with Plan Line and PO #
        Div topSection = createDialogTopSection();
        mainContainer.appendChild(topSection);
        
        // List Size section
        Div sizeSection = createListSizeSection();
        mainContainer.appendChild(sizeSection);
        
        // List Barcode section
        Div barcodeSection = createListBarcodeSection();
        mainContainer.appendChild(barcodeSection);
        
        // Close button - positioned to be always visible
        Div buttonSection = new Div();
        buttonSection.setStyle("text-align: right; margin-top: 20px; padding: 15px 0; border-top: 1px solid #e5e7eb; flex-shrink: 0;");
        Button closeButton = new Button("Close");
        closeButton.setStyle("background: #6b7280; color: white; border: none; padding: 10px 24px; border-radius: 6px; cursor: pointer; font-weight: 500; min-width: 80px;");
        closeButton.addEventListener("onClick", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                dialog.detach();
            }
        });
        buttonSection.appendChild(closeButton);
        mainContainer.appendChild(buttonSection);
        
        dialog.appendChild(mainContainer);
        dialog.setPage(this.getPage());
        dialog.doHighlighted();
    }
    
    private Div createDialogTopSection() {
        Div topSection = new Div();
        topSection.setStyle("display: flex; gap: 20px; align-items: end;");
        
        // Plan Line field - read only
        Div planLineDiv = new Div();
        planLineDiv.setStyle("flex: 1;");
        Label planLabel = new Label("Plan Line");
        planLabel.setStyle("display: block; margin-bottom: 5px; font-weight: bold;");
        
     
        String planLineText = "";
        if (planLineCombo != null && planLineCombo.getSelectedItem() != null) {
            planLineText = planLineCombo.getSelectedItem().getLabel();
        }
        Textbox planField = new Textbox(planLineText);
        planField.setStyle(StyleManager.combine(
                StyleManager.getInputEditable(),
                "border: 2px solid #3b82f6;"));
        planField.setHflex("1");
        planField.setReadonly(true);
        planLineDiv.appendChild(planLabel);
        planLineDiv.appendChild(planField);
        
        // PO # field - read only
        Div poDiv = new Div();
        poDiv.setStyle("flex: 1;");
        Label poLabel = new Label("PO #");
        poLabel.setStyle("display: block; margin-bottom: 5px; font-weight: bold;");
     
        String poText = "";
        if (poCombo != null && poCombo.getSelectedItem() != null) {
            poText = poCombo.getSelectedItem().getLabel();
        }
        Textbox poField = new Textbox(poText);
        poField.setStyle(StyleManager.combine(
                StyleManager.getInputEditable(),
                "border: 2px solid #3b82f6;"));
        poField.setHflex("1");
        poField.setReadonly(true);
        poDiv.appendChild(poLabel);
        poDiv.appendChild(poField);
        
        topSection.appendChild(planLineDiv);
        topSection.appendChild(poDiv);
        
        return topSection;
    }
    
    private Div createListSizeSection() {
        Div sizeSection = new Div();
        sizeSection.setStyle("border: 2px solid #3b82f6; border-radius: 8px; padding: 15px; min-width: 200px;");

        Label sizeHeader = new Label("List Size");
        sizeHeader.setStyle("display: block; text-align: center; font-weight: bold; color: #3b82f6; margin-bottom: 15px; font-size: 16px;");
        sizeSection.appendChild(sizeHeader);

        Div sizeButtonsContainer = new Div();
        sizeButtonsContainer.setStyle("display: flex; gap: 15px; justify-content: center; flex-wrap: wrap;");
        sizeSection.appendChild(sizeButtonsContainer);

        List<Button> sizeButtons = onCreateSizeButtons(sizeButtonsContainer);
        if (sizeButtons.isEmpty()) {
            Label noSizeLabel = new Label("No sizes available for selected PO");
            noSizeLabel.setStyle("color: #6b7280; font-style: italic; text-align: center;");
            sizeButtonsContainer.appendChild(noSizeLabel);
        } else {
            for (Button sizeButton : sizeButtons) {
                sizeButtonsContainer.appendChild(sizeButton);
            }

            // Simulate first click
            Events.postEvent("onClick", sizeButtons.get(0), null);
        }

        return sizeSection;
    }
    
    private void applySizeButtonStyleAndListener(Button button, Div container) {
        button.setStyle("padding: 10px 20px; border: 2px solid #3b82f6; background: white; color: #3b82f6; border-radius: 4px; cursor: pointer; font-weight: bold; min-width: 60px;");
        button.addEventListener("onClick", event -> {
            highlightSelectedSizeButton(container, button);
            loadBarcodeBySize(button.getLabel());
        });
    }

    private void highlightSelectedSizeButton(Div container, Button selectedButton) {
        for (Component child : container.getChildren()) {
            if (child instanceof Button btn) {
                btn.setStyle("padding: 10px 20px; border: 2px solid #3b82f6; background: white; color: #3b82f6; border-radius: 4px; cursor: pointer; font-weight: bold; min-width: 60px;");
            }
        }

        selectedButton.setStyle("padding: 10px 20px; border: 2px solid #3b82f6; background: #3b82f6; color: white; border-radius: 4px; cursor: pointer; font-weight: bold; min-width: 60px;");
    }

    private void loadBarcodeBySize(String size) {
        barcodeContainer.getChildren().clear();

        List<Map<String, Object>> data = onCreateBarcodeListBySize(size);
        for (int i = 0; i < data.size(); i++) {
            Div item = createBarcodeItem(data.get(i), i);
            barcodeContainer.appendChild(item);
        }
    }
    
    private Div createListBarcodeSection() {
        Div barcodeSection = new Div();
        barcodeSection.setStyle("border: 2px solid #3b82f6; border-radius: 8px; padding: 20px; flex: 1; min-height: 320px; max-height: 350px; overflow-y: auto;");

        Label header = new Label("List Barcode");
        header.setStyle("display: block; text-align: center; font-weight: bold; color: #3b82f6; margin-bottom: 15px; font-size: 16px;");
        barcodeSection.appendChild(header);

        barcodeContainer = new Div();
        barcodeContainer.setStyle("display: flex; gap: 12px; flex-wrap: wrap; justify-content: space-between; align-items: flex-start; padding: 0 5px;");
        barcodeSection.appendChild(barcodeContainer);

        return barcodeSection;
    }
    
    private Div createBarcodeItem(Map<String, Object> data, int index) {
        Div item = new Div();
        item.setStyle("border: 1px solid #d1d5db; border-radius: 8px; padding: 10px; width: 180px; background: white; margin-bottom: 0; align-self: flex-start;");

        Label successLabel = new Label();
        successLabel.setStyle("background: #10b981; color: white; padding: 2px 6px; border-radius: 3px; font-weight: bold; font-size: 10px; text-align: center; width: 60px; margin-bottom: 5px; visibility: hidden;");
                
        String barcodeData = (String) data.get("barcode");
        Div qrCode = QRCodeUtil.createQRCodeWithStyle(barcodeData, 60, "margin-bottom: 10px;");
                
        Div sizeQtySection = new Div();
        sizeQtySection.setStyle("display: flex; gap: 10px; margin-bottom: 10px;");

        Div sizeDiv = new Div();
        sizeDiv.setStyle("flex: 1;");
        Label sizeLabel = new Label("Size");
        sizeLabel.setStyle("font-size: 12px; color: #6b7280;");
        Textbox sizeInput = new Textbox((String) data.get("sizefactory"));
        sizeInput.setReadonly(true);
        sizeInput.setStyle("width: 100%; padding: 4px; border: 1px solid #d1d5db; border-radius: 2px; margin-top: 2px; background: #f9fafb;");
        sizeDiv.appendChild(sizeLabel);
        sizeDiv.appendChild(sizeInput);

        Div qtyDiv = new Div();
        qtyDiv.setStyle("flex: 1;");
        Label qtyLabel = new Label("Qty");
        qtyLabel.setStyle("font-size: 12px; color: #6b7280;");
        Textbox qtyInput = new Textbox(String.valueOf(data.get("qtybarcode")));
        qtyInput.setReadonly(true);
        qtyInput.setStyle("width: 100%; padding: 4px; border: 1px solid #d1d5db; border-radius: 2px; margin-top: 2px; text-align: center; font-weight: bold; background: #f9fafb;");
        qtyDiv.appendChild(qtyLabel);
        qtyDiv.appendChild(qtyInput);

        sizeQtySection.appendChild(sizeDiv);
        sizeQtySection.appendChild(qtyDiv);

     // Fixed DetailDiv dengan format yang benar
        Div detailsDiv = new Div();
        detailsDiv.setStyle("font-size: 11px; color: #374151; line-height: 1.3;");
        
        Label itemLabel = new Label("Item : " + data.get("itemcode"));
        itemLabel.setStyle("display: block; margin-bottom: 2px;");
        
        Label descLabel = new Label("Desc : " + data.get("itemdesc"));
        descLabel.setStyle("display: block; margin-bottom: 2px;");
        
        Label poLabel = new Label("PO    : " + data.get("poreference"));
        poLabel.setStyle("display: block; margin-bottom: 2px;");
        
        detailsDiv.appendChild(itemLabel);
        detailsDiv.appendChild(descLabel);
        detailsDiv.appendChild(poLabel);

        if (index == 3) {
            Label badge = new Label("1");
            badge.setStyle("position: absolute; right: 5px; top: 5px; background: #3b82f6; color: white; padding: 2px 6px; border-radius: 2px; font-weight: bold; font-size: 12px;");
            item.setStyle(item.getStyle() + " position: relative; border: 2px solid #3b82f6;");
            item.appendChild(badge);
        }

        item.appendChild(qrCode);
        item.appendChild(successLabel);
        item.appendChild(sizeQtySection);
        item.appendChild(detailsDiv);

        item.addEventListener("onClick", evt -> {
        	String barcode = (String) data.get("barcode");
        	String errorMessage = onCreateBarcodeItem(barcode);
        	HighlightClickBarcode(item);
            showResultMessageBarcode(successLabel, item, errorMessage);
        });
                
        return item;
    }
    
    private void HighlightClickBarcode(Div item) {
       
        String originalStyle = item.getStyle();
        
        // Apply highlight immediately
        String highlightStyle = originalStyle.replace("background: white;", "background: #e0f2fe;")
                                           .replace("border: 1px solid #d1d5db;", "border: 2px solid #0ea5e9;")
                                           + " box-shadow: 0 4px 12px rgba(14, 165, 233, 0.3);";
        item.setStyle(highlightStyle);
        
        // Remove highlight after short delay (jika item tidak di-remove)
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                try {
                    Executions.schedule(desktop, evt -> {
                        if (item.getParent() != null) {
                            item.setStyle(originalStyle);
                        }
                    }, null);
                } catch (Exception e) {
                    log.log(Level.WARNING, "Failed to remove highlight", e);
                }
            }
        }, 300); 
    }
    
    private void showResultMessageBarcode(Label successLabel, Div item, String errorMessage) {
        if (errorMessage == null || errorMessage.isEmpty()) {
            
            successLabel.setValue("Success ✓");
            successLabel.setStyle("background: #10b981; color: white; padding: 2px 6px; border-radius: 3px; font-weight: bold; font-size: 10px; text-align: center; width: 60px; margin-bottom: 5px; visibility: visible; display: block;");
            
            // Remove item setelah 800ms
            new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    Executions.schedule(desktop, evt -> item.detach(), null);
                }
            }, 800);
            
            this.onLoadTableList();
            
        } else {
            // Failed case
            successLabel.setValue("Failed ❌");
            successLabel.setStyle("background: #ef4444; color: white; padding: 2px 6px; border-radius: 3px; font-weight: bold; font-size: 10px; text-align: center; width: 60px; margin-bottom: 5px; visibility: visible; display: block;");
            
            // Show error popup
            showMessageWindow(errorMessage);
            
            // Hide message setelah 3 detik
            new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                    Executions.schedule(desktop, evt -> successLabel.setStyle("visibility: hidden;"), null);
                }
            }, 4000);
        }
    }
    
    private void showMessageWindow(String errorMessage) {
        try {
            org.zkoss.zul.Messagebox.show(errorMessage, "Error", 
                org.zkoss.zul.Messagebox.OK, org.zkoss.zul.Messagebox.ERROR);
        } catch (Exception e) {
            log.warning("Failed to show error: " + errorMessage);
        }
    }
    
    private void showMessageWindow(String message, String title, int buttons, String icon) {
        try {
            org.zkoss.zul.Messagebox.show(message, title, buttons, icon);
        } catch (Exception e) {
            log.warning("Failed to show message: " + message);
        }
    }

    /* ------------------------------------------------------------------------------------------------------------------------------------------------
     *  Window Dialog Report Detail Output
     * ------------------------------------------------------------------------------------------------------------------------------------------------
     */
    private void showReportDetail() {
        try {
            Window window = new Window();
            window.setTitle("Report Detail");
            window.setBorder("normal");
            window.setClosable(true);
            window.setWidth("1200px");
            window.setHeight("600px");
            window.setMaximizable(true);

            Vbox container = new Vbox();
            container.setStyle("padding: 20px;");
            container.setSpacing("15px");
            container.setVflex("1");

            // Simple title
            Label title = new Label("Data Output Scan per Pair per Jam");
            title.setStyle("font-size: 24px; font-weight: bold; text-align: center; width: 100%;");
            container.appendChild(title);

            // Simple table
            Listbox table = onCreateTableReportDetail();
            table.setVflex("1");
            container.appendChild(table);

            // Simple close button
            Button closeBtn = new Button("Close");
            closeBtn.setStyle(
                    "background: #6c757d; color: white; padding: 10px 25px; border: none; border-radius: 4px; font-weight: bold; margin: 0 auto; display: block;");
            closeBtn.addEventListener(Events.ON_CLICK, e -> window.detach());
            container.appendChild(closeBtn);

            window.appendChild(container);
            this.appendChild(window);

            window.doModal();

        } catch (Exception e) {
            showMessageWindow("Failed to show report" + e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);

        }
    }
    
    private Listbox createTableReportDetail(List<Map<String, Object>> data) {
        Listbox table = new Listbox();
        table.setVflex("1");
        table.setStyle("width: 100%; border: 1px solid #ccc; border-radius: 6px;");

        String[] headers = { "No", "PO Number", "Total", "Jam 1", "Jam 2", "Jam 3", "Jam 4", "Jam 5", "Jam 6", "Jam 7", "Jam 8", "Jam 9" , "Jam 10"};

        // Create header
        org.zkoss.zul.Listhead listhead = new org.zkoss.zul.Listhead();
        listhead.setStyle("background: #f9fafb; border-bottom: 1px solid #e5e7eb; height: 32px;");
        
        for (int i = 0; i < headers.length; i++) {
            org.zkoss.zul.Listheader h = new org.zkoss.zul.Listheader(headers[i]);
            h.setHflex(i == 1 ? "2" : "1"); // PO Number column wider
            h.setStyle("text-align: center; font-weight: bold; color: #475569; font-size: 12px; padding: 4px;");
            listhead.appendChild(h);
        }
        table.appendChild(listhead);

        // Variables for footer calculation
        int totalSum = 0;
        int[] hourTotals = new int[10];

        // Add data rows or empty message
        if (data.isEmpty()) {
            org.zkoss.zul.Listitem empty = new org.zkoss.zul.Listitem();
            org.zkoss.zul.Listcell cell = new org.zkoss.zul.Listcell("No data available");
            cell.setSpan(headers.length);
            cell.setStyle("text-align: center; padding: 20px; color: #6b7280; font-style: italic;");
            empty.appendChild(cell);
            table.appendChild(empty);
        } else {
            int rowNum = 1;
            for (Map<String, Object> rowData : data) {
                org.zkoss.zul.Listitem row = new org.zkoss.zul.Listitem();
                row.setStyle("height: 32px;");

                // Add cells
                row.appendChild(new org.zkoss.zul.Listcell(String.valueOf(rowNum++)));
                row.appendChild(new org.zkoss.zul.Listcell((String) rowData.get("poNumber")));
                
                int total = (Integer) rowData.get("total");
                row.appendChild(new org.zkoss.zul.Listcell(String.valueOf(total)));
                totalSum += total;

                // Hour columns (Jam 1-8)
                for (int i = 1; i <= 10; i++) {
                    int hourValue = (Integer) rowData.get("hour" + i);
                    row.appendChild(new org.zkoss.zul.Listcell(String.valueOf(hourValue)));
                    hourTotals[i - 1] += hourValue;
                }

                table.appendChild(row);
            }
        }

        // Create fixed footer
        org.zkoss.zul.Listfoot listfoot = new org.zkoss.zul.Listfoot();
        listfoot.setStyle("background: #f9fafb; border-top: 2px solid #e5e7eb; height: 36px; font-weight: bold;");
        
        listfoot.appendChild(new org.zkoss.zul.Listfooter("")); // No
        
        org.zkoss.zul.Listfooter poFooter = new org.zkoss.zul.Listfooter("TOTAL");
        poFooter.setStyle("text-align: center; font-weight: bold; color: #374151;");
        listfoot.appendChild(poFooter);
        
        org.zkoss.zul.Listfooter totalFooter = new org.zkoss.zul.Listfooter(String.valueOf(totalSum));
        totalFooter.setStyle("text-align: center; font-weight: bold; color: #374151; background: #fef3c7;");
        listfoot.appendChild(totalFooter);
        
        // Hour totals
        for (int hourTotal : hourTotals) {
            org.zkoss.zul.Listfooter hourFooter = new org.zkoss.zul.Listfooter(String.valueOf(hourTotal));
            hourFooter.setStyle("text-align: center; font-weight: bold; color: #374151;");
            listfoot.appendChild(hourFooter);
        }
        
        table.appendChild(listfoot);
        return table;
    }

    
    
    /* ------------------------------------------------------------------------------------------------------------------------------------------------
     * LOGIC METHOD DATABASE
     * ------------------------------------------------------------------------------------------------------------------------------------------------
     */
    
    public String onCreateOrGetDraft() {
        Properties ctx = Env.getCtx();
        int AD_User_ID = Env.getAD_User_ID(ctx);
        
        String trxName = Trx.createTrxName(MESConstants.TransactionType.SCAN_SEWING);
        Trx trx = null;
        
        try {
            trx = Trx.get(trxName, true);
            trx.setDisplayName(MMES_ProdOutput.class.getName() + "_createOrGetDraft");

            // Try to find existing draft for current user
            MMES_ProdOutput existingDraft = MMES_ProdOutput.getDraftForUser(ctx, AD_User_ID, MESConstants.TransactionType.SCAN_SEWING);
            
            if (existingDraft != null) {
                trx.commit();
                return existingDraft.getDocumentNo();
            }

            // Create new draft document
            MMES_ProdOutput newDraft = new MMES_ProdOutput(ctx, 0, trxName);
            newDraft.setMES_Trans_ID(MESConstants.TransactionType.SCAN_SEWING); 
            newDraft.setDocStatus(DocAction.STATUS_Drafted);
            newDraft.saveEx();

            trx.commit();
            
            return newDraft.getDocumentNo(); 

        } catch (Exception e) {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            throw new IllegalStateException("Failed to create or get draft: " + e.getMessage(), e);
            
        } finally {
            if (trx != null && trx.isActive()) {
                trx.close();
            }
        }
    }
    
    private Combobox onLoadPlanLineComboBox(Combobox combo) {
        if (combo == null) {
            combo = new Combobox();
            combo.setHflex("1");
        } else {
            combo.getItems().clear();
        }

        List<MSAP_WorkCenter> workCenterList = MSAP_WorkCenter.getAvailableWorkCentersByProduction(
            Env.getCtx(),
            "ZFG",
            MESConstants.TransactionType.SCAN_SEWING,
            null
        );

        for (MSAP_WorkCenter wc : workCenterList) {
            combo.appendItem(wc.getName(), wc.getSAP_WorkCenter_ID());
        }

        if (!workCenterList.isEmpty()) {
            combo.setSelectedIndex(0);
        }

        return combo;
    }

    
    private Combobox onLoadPOSOComboBox(Combobox combo, int slocReceiving) {
    	
        if (combo == null) {
            combo = new Combobox();
            combo.setHflex("1");
        } else {
            combo.getItems().clear();
        }

        List<String> poList = MV_MES_ProductionInfo.getOutstandingPOSOList(
            Env.getCtx(),
            "ZFG",
            slocReceiving,
            MESConstants.TransactionType.SCAN_SEWING,
            null
        );

        for (String poRef : poList) {
            combo.appendItem(poRef, poRef);
        }

        if (!poList.isEmpty()) {
            combo.setSelectedIndex(0);
        }

        return combo;
    }  
 
    private List<Button> onCreateSizeButtons(Div container) {
    	
        List<Button> buttons = new ArrayList<>();

        String selectedPO = (poCombo != null && poCombo.getSelectedItem() != null)
                ? poCombo.getSelectedItem().getLabel()
                : "";

        int selectedPlanLine = 0;
        if (planLineCombo != null && planLineCombo.getItemCount() > 0) {
            selectedPlanLine = (Integer) planLineCombo.getItemAtIndex(0).getValue();
        }

        List<MV_MES_ProductionInfo> infoList = MV_MES_ProductionInfo.findBarcodeSizeAvailabilityList(
                Env.getCtx(), "ZFG", selectedPlanLine, selectedPO, null
        );

        Set<String> uniqueSizes = new HashSet<>();
        for (MV_MES_ProductionInfo info : infoList) {
            String size = info.getSizeFactory();
            if (size != null && !size.trim().isEmpty() && uniqueSizes.add(size)) {
                Button sizeButton = new Button(size);
                applySizeButtonStyleAndListener(sizeButton, container);
                buttons.add(sizeButton);
            }
        }

        return buttons;
    }

    private List<Map<String, Object>> onCreateBarcodeListBySize(String size) {
    	
        String selectedPO = (poCombo != null && poCombo.getSelectedItem() != null)
                ? poCombo.getSelectedItem().getLabel()
                : "";

        List<Map<String, Object>> barcodeList = new ArrayList<>();

        String sql = """
            SELECT 
                mb.poreference,
                mp.value AS itemcode,
                mp.name AS itemdesc,
                mb2.barcode,
                mb2.sizefactory,
                mb2.qtybarcode
            FROM mes_barcode mb
            INNER JOIN mes_barcodedetail mb2 ON mb.mes_barcode_id = mb2.mes_barcode_id
            INNER JOIN MES_BarcodeType bt ON bt.MES_BarcodeType_ID = mb.MES_BarcodeType_ID
            INNER JOIN m_product mp ON mb.m_product_id = mp.m_product_id
            WHERE bt.value = 'B2'
              AND mb.poreference = ?
              AND mb2.sizefactory = ?
            ORDER BY mb2.barcode
        """;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql, null);
            DB.setParameters(pstmt, new Object[]{selectedPO, size});
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("poreference", rs.getString("poreference"));
                row.put("itemcode", rs.getString("itemcode"));
                row.put("itemdesc", rs.getString("itemdesc"));
                row.put("barcode", rs.getString("barcode"));
                row.put("sizefactory", rs.getString("sizefactory"));
                row.put("qtybarcode", rs.getInt("qtybarcode"));
                barcodeList.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt);
        }

        return barcodeList;
    }


    private String onCreateBarcodeItem(String barcode) {
        
        Properties ctx = Env.getCtx();
        
        String selectedPO = (poCombo != null && poCombo.getSelectedItem() != null)
                 ? poCombo.getSelectedItem().getLabel()
                 : "";

        try {
            // Get data barcode detail
            MMES_BarcodeDetail barcodeDatas = MMES_BarcodeDetail.findByBarcodeAndType(ctx, barcode, MESConstants.BarcodeType.BARCODE_SEWING);
            
            if (barcodeDatas == null) {
                return "Barcode '" + barcode + "' tidak ditemukan di database UPC";
            }

            // Get data produksi stock
            MV_MES_ProductionInfo stockDatas = MV_MES_ProductionInfo.findByPOAndSizeFactory(ctx, 
            		selectedPO, barcodeDatas.getSizeFactory(), MESConstants.TransactionType.SCAN_SEWING, null);
            
            if (stockDatas == null) {
                return "PO belum release atau tidak ditemukan dalam data produksi";
            } else if (stockDatas.getQtyOutstanding().compareTo(BigDecimal.ZERO) <= 0) {
                return "Data PO sudah completed";
            }

            // Save Transaction Detail
            String trxName = Trx.createTrxName("MSA_Detail");
            Trx trx = null;
            try {
                trx = Trx.get(trxName, true);
                trx.setDisplayName(getClass().getName() + "_addBarcodeItem");

                MMES_ProdOutput prodOutput = MMES_ProdOutput.getDraftForUser(ctx, Env.getAD_User_ID(ctx), MMES_ProdOutput.MES_TRANS_ID_ScanSewing);

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
                prodOutputDetail.setQtyEntered(barcodeDatas.getQtyBarcode());
                prodOutputDetail.saveEx();

                trx.commit();
                
                return null;
                
            } catch (Exception e) {
                if (trx != null && trx.isActive()) {
                    trx.rollback();
                }
                return "Gagal menyimpan data: " + e.getMessage();
                
            } finally {
                if (trx != null && trx.isActive()) {
                    trx.close();
                }
            }
            
        } catch (Exception e) {
            return "System error: " + e.getMessage();
        }
    }
    
    private void onLoadTableList() {
        Properties ctx = Env.getCtx();
        
        // Get current draft document
        MMES_ProdOutput prodOutput = MMES_ProdOutput.getDraftForUser(ctx, Env.getAD_User_ID(ctx), MMES_ProdOutput.MES_TRANS_ID_ScanSewing);
        
        if (prodOutput == null) {
            clearTableData();
            return;
        }

        // Query to get all MES_ProdOutputDetail records for current document
        String whereClause = "MES_ProdOutput_ID=? AND AD_Client_ID=?";
        List<MMES_ProdOutputDetail> details = new Query(ctx, MMES_ProdOutputDetail.Table_Name, whereClause, null)
                .setParameters(prodOutput.getMES_ProdOutput_ID(), Env.getAD_Client_ID(ctx))
                .setOrderBy("Created DESC")
                .list();
        
        clearTableData();
        
        int rowNo = 1;
        for (MMES_ProdOutputDetail detail : details) {
            addTableRow(
                String.valueOf(rowNo++),
                detail.getBarcode() != null ? detail.getBarcode() : "",
                detail.getPOReference() != null ? detail.getPOReference() : "",
                detail.getArticle() != null ? detail.getArticle() : "N/A",
                detail.getSizeFactory() != null ? detail.getSizeFactory() : "N/A",
                detail.getQtyEntered() != null ? detail.getQtyEntered().toString() : "1"
            );
        }
        
        // Update total count in footer
        this.updateTableFooter(details.size());
        
        this.onLoadScanStatistics();
    }
    
    private void onDeleteTableRow(org.zkoss.zul.Listitem item, String barcode) {
        Properties ctx = Env.getCtx();
        String trxName = Trx.createTrxName("DeleteRow");
        Trx trx = null;
        
        try {
            trx = Trx.get(trxName, true);
            trx.setDisplayName(getClass().getName() + "_deleteTableRow");

            // Get current draft document
            MMES_ProdOutput prodOutput = MMES_ProdOutput.getDraftForUser(ctx, Env.getAD_User_ID(ctx), MMES_ProdOutput.MES_TRANS_ID_ScanSewing);
            
            if (prodOutput != null) {
                // Find and delete the detail record
                String whereClause = "MES_ProdOutput_ID=? AND Barcode=? AND AD_Client_ID=?";
                MMES_ProdOutputDetail detail = new Query(ctx, MMES_ProdOutputDetail.Table_Name, whereClause, trxName)
                        .setParameters(prodOutput.getMES_ProdOutput_ID(), barcode, Env.getAD_Client_ID(ctx))
                        .first();
                
                if (detail != null) {
                    detail.deleteEx(true);
                    trx.commit();
                    
                    // Remove from UI
                    item.detach();
                    
                    // Refresh row numbers
                    this.refreshRowNumbers();
                    this.onLoadScanStatistics();
                    
                }
            }
            
        } catch (Exception e) {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            log.log(Level.SEVERE, "Error deleting row with barcode: " + barcode, e);
            throw e;
            
        } finally {
            if (trx != null && trx.isActive()) {
                trx.close();
            }
        }
    }
    
    private void onLoadScanStatistics() {
        Properties ctx = Env.getCtx();
        
        // Get current draft document
        MMES_ProdOutput prodOutput = MMES_ProdOutput.getDraftForUser(ctx, Env.getAD_User_ID(ctx), MMES_ProdOutput.MES_TRANS_ID_ScanSewing);
        
        if (prodOutput == null) {
            scanPerHourLabel.setValue("0");
            totalScanLabel.setValue("0");
            return;
        }
        
        int[] stats = fetchScanStatistics(prodOutput.getMES_ProdOutput_ID());
        
        // Update UI labels
        scanPerHourLabel.setValue(String.valueOf(stats[1]));
        totalScanLabel.setValue(String.valueOf(stats[2])); 
        
    }

    private int[] fetchScanStatistics(int prodOutputId) {
    	
    	Properties ctx = Env.getCtx();
    	
        String sql = """
            SELECT 
                GREATEST(0, EXTRACT(HOUR FROM CURRENT_TIMESTAMP) - 6) AS workhour,
                
                COALESCE(SUM(CASE 
                    WHEN mpod.created >= (CURRENT_TIMESTAMP - INTERVAL '1 hour') 
                    THEN mpod.qtyentered ELSE 0 
                END), 0) AS total_perhour,

                COALESCE(SUM(CASE 
                    WHEN mpod.created >= (CURRENT_DATE + INTERVAL '7 hours') 
                         AND mpod.created <= CURRENT_TIMESTAMP 
                    THEN mpod.qtyentered ELSE 0 
                END), 0) AS total_today

            FROM mes_prodoutput mpo
            INNER JOIN mes_prodoutputdetail mpod 
                ON mpo.mes_prodoutput_id = mpod.mes_prodoutput_id
            WHERE 
        		mpo.ad_client_id = ? AND
        		DATE(mpod.created) = CURRENT_DATE AND 
        		mpod.isactive = 'Y' AND
        		mpod.isactive = 'Y' AND
        		mpo.createdby = ? AND
        		mpo.mes_trans_id = ?
        		
            """;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = DB.prepareStatement(sql, null);
            DB.setParameters(pstmt, new Object[]{Env.getAD_Client_ID(ctx),Env.getAD_User_ID(ctx),MESConstants.TransactionType.SCAN_SEWING});
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int workHour = rs.getInt("workhour");
                int totalPerHour = rs.getInt("total_perhour");
                int totalToday = rs.getInt("total_today");
                
                return new int[]{workHour, totalPerHour, totalToday};
            }
            
            return new int[]{0, 0, 0};
            
        } catch (Exception e) {
            return new int[]{0, 0, 0};
            
        } finally {
            DB.close(rs, pstmt);
        }
    }
    
    private Listbox onCreateTableReportDetail() throws Exception {
    	List<Map<String, Object>> data = fetchSewingReportData();
        return this.createTableReportDetail(data);
    }

    
    private List<Map<String, Object>> fetchSewingReportData() throws Exception {
    	Properties ctx = Env.getCtx();
        List<Map<String, Object>> dataList = new ArrayList<>();

        String sql = """
        	SELECT 
			    mpd.poreference,
			    SUM(mpd.qtyentered) AS total,
			
			    SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 7 THEN mpd.qtyentered ELSE 0 END) AS hour1,
			    SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 8 THEN mpd.qtyentered ELSE 0 END) AS hour2,
			    SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 9 THEN mpd.qtyentered ELSE 0 END) AS hour3,
			    SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 10 THEN mpd.qtyentered ELSE 0 END) AS hour4,
			    SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 11 THEN mpd.qtyentered ELSE 0 END) AS hour5,
			    SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 12 THEN mpd.qtyentered ELSE 0 END) AS hour6,
			    SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 13 THEN mpd.qtyentered ELSE 0 END) AS hour7,
			    SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 14 THEN mpd.qtyentered ELSE 0 END) AS hour8,
			    SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 15 THEN mpd.qtyentered ELSE 0 END) AS hour9,
			    SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 16 THEN mpd.qtyentered ELSE 0 END) AS hour10,
			    SUM(CASE WHEN EXTRACT(HOUR FROM mpd.created) = 17 THEN mpd.qtyentered ELSE 0 END) AS hour11
			
			FROM mes_prodoutput mp
			JOIN mes_prodoutputdetail mpd 
			    ON mp.mes_prodoutput_id = mpd.mes_prodoutput_id
			
			WHERE 
			    mp.ad_client_id = ?
			    AND mpd.createdby = ?
			    AND mp.mes_trans_id = ?
			    AND mpd.created >= (CURRENT_DATE + INTERVAL '7 hours')
			    AND mpd.created <= CURRENT_TIMESTAMP
			
			GROUP BY mpd.poreference
			ORDER BY mpd.poreference;
        """;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
        	pstmt = DB.prepareStatement(sql, null);
            DB.setParameters(pstmt, new Object[]{Env.getAD_Client_ID(ctx),Env.getAD_User_ID(ctx),MESConstants.TransactionType.SCAN_SEWING});
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("poNumber", rs.getString("poreference"));
                row.put("total", rs.getInt("total"));
                for (int i = 1; i <= 10; i++) {
                    row.put("hour" + i, rs.getInt("hour" + i));
                }
                dataList.add(row);
            }

        } catch (Exception e) {
            throw new Exception("Error executing report query: " + e.getMessage(), e);
        } finally {
            DB.close(rs, pstmt);
        }

        return dataList;
    }

    private void onSubmit() {
        try {
            Properties ctx = Env.getCtx();
            
            // Get draft document
            MMES_ProdOutput prodOutput = MMES_ProdOutput.getDraftForUser(ctx, Env.getAD_User_ID(ctx), MMES_ProdOutput.MES_TRANS_ID_ScanSewing);
            
            if (prodOutput == null) {
                showMessageWindow("No document to submit", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
                return;
            }

            // Check if has data
            String whereClause = "MES_ProdOutput_ID=? AND AD_Client_ID=?";
            List<MMES_ProdOutputDetail> details = new Query(ctx, MMES_ProdOutputDetail.Table_Name, whereClause, null)
                    .setParameters(prodOutput.getMES_ProdOutput_ID(), Env.getAD_Client_ID(ctx))
                    .list();
            
            if (details.isEmpty()) {
                showMessageWindow("No data to submit", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
                return;
            }

            // Confirm submit
            Messagebox.show("Submit " + details.size() + " items?", "Confirm", 
                Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, event -> {
                    if (Messagebox.ON_YES.equals(event.getName())) {
                        doSubmit(prodOutput);
                    }
                });
            
        } catch (Exception e) {
            showMessageWindow("Error: " + e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
        }
    }

    private void doSubmit(MMES_ProdOutput prodOutput) {
        String trxName = Trx.createTrxName();
        Trx trx = null;
        
        try {
        	trx = Trx.get(trxName, true);
            trx.setDisplayName(getClass().getName() + "_submitDocument");
            
            // Change status to completed with proper transaction context
            prodOutput.set_TrxName(trxName); 
            //prodOutput.setDocStatus(DocAction.STATUS_Completed);
            //prodOutput.setProcessed(true);
            prodOutput.setDocAction(DocAction.ACTION_Complete); 
            
            prodOutput.saveEx();
            if (prodOutput.processIt(trxName)) {
            	showMessageWindow("Document submitted successfully!", "Success", Messagebox.OK, Messagebox.INFORMATION);
            } else {
            	throw new Exception("Failled");
            }
            
            ///prodOutput.processIt(trxName);
            trx.commit();
            
            // Show completed status briefly
            setStatusInfo("Completed");
            
            // Create new draft and reset UI
            String newDocumentNo = onCreateOrGetDraft();
            documentNoField.setValue(newDocumentNo);
            this.onLoadTableList();
            this.setStatusInfo("Draft");
            
            
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            showMessageWindow("Submit failed: " + e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
        } finally {
            if (trx != null) trx.close();
        }
        
        
    }
    

    
}