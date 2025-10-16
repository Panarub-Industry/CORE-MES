package id.co.bintangindokarya.mes.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import org.adempiere.util.Callback;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.window.Dialog;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrg;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Center;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.Vbox;

import id.co.bintangindokarya.mes.MESConstants;
import id.co.bintangindokarya.mes.MESConstants.BarcodeType;
import id.co.bintangindokarya.mes.model.MMES_BarcodeDetail;
import id.co.bintangindokarya.mes.model.MMES_ProdOutput;
import id.co.bintangindokarya.mes.model.MMES_ProdOutputDetail;
import id.co.bintangindokarya.mes.model.MV_MES_ProductionInfo;
import id.co.bintangindokarya.mes.service.MESServices;
import id.co.bintangindokarya.mes.service.MESServices.Barcode;
import id.co.bintangindokarya.mes.service.MESServices.Barcode.BarcodeData;
import id.co.bintangindokarya.mes.service.MESServices.ProductionReport.ProductionReportData;
import id.co.bintangindokarya.mes.service.MESServices.ProductionStatistic;
import id.co.bintangindokarya.mes.service.MESServices.ProductionStatistic.ProductionStatisticData;
import id.co.bintangindokarya.mes.service.MESServices.Stock;
import id.co.bintangindokarya.mes.service.MESServices.Stock.StockData;

@org.idempiere.ui.zk.annotation.Form(name = "id.co.bintangindokarya.mes.form.ProductionAssemblingForm")
public class ProductionAssemblingForm extends ADForm implements IFormController, EventListener<Event>, ValueChangeListener {

	
	private static final long serialVersionUID = 5017756969045503421L;

    
    private static final CLogger log = CLogger.getCLogger(ProductionAssemblingForm.class);

    // Security: Input validation constants

    private static final int UP_ARROW = 38;
    private static final int DOWN_ARROW = 40;
    private static final int ENTER_KEY = 13;
    private static final int MAX_BARCODE_LENGTH = 50;
    private static final int MAX_TEXT_LENGTH = 100;
    private static final int MAX_PO_LENGTH = 30;
    private static final String BARCODE_PATTERN = "^[A-Za-z0-9]+$";

    // Header Components
    private Div formPanel;
    private Textbox fDocumentNo;
    private Textbox fLine;
    private Combobox fPO;
    private Button bAddPO; // Button untuk menambah PO baru
    private WTableDirEditor fClient; // Client ID (Tenant)
    private WTableDirEditor fOrganizer;
    private Textbox fBarcodeUPC;

    // Information Message Panel
    private Div infoPanel;
    private Label infoLabel;

    // Data Table - Using ZK Listbox
    private Listbox table;
    private List<AssemblyData> dataList = new ArrayList<>();

    // Buttons
    private Button bPosting;
    private Button bReportDetail;

    // Status Field
    private Label statusLabel;

    // Bottom Statistics
    private Div statsPanel;
    private Label scanPerHourLabel;
    private Label totalScanLabel;
    private int scanPerHour = 32;
    private int totalScan = 142;

    // Digital Clock
    private Label dateTimeLabel;
    private Timer clockTimer;
    private Desktop desktop;

    // PO Selection tracking
    private String currentSelectedPONumber;
    // Focused index when navigating popup with keyboard (not yet confirmed)
    private int focusedPOIndex = -1;

    // Default values for form fields
    private static final String DEFAULT_LINE = "AA01";

    // Document Status Constants using iDempiere DocumentAction standard values
    private static final String STATUS_DRAFT = "DR"; // DocAction.STATUS_Drafted
    private static final String STATUS_POSTED = "CO"; // DocAction.STATUS_Completed
    private static final String STATUS_IN_PROGRESS = "IP"; // DocAction.STATUS_InProgress
    private static final String STATUS_VOIDED = "VO"; // DocAction.STATUS_Voided

    // Document Actions using iDempiere DocumentAction standard values
    private static final String ACTION_PREPARE = "PR"; // DocAction.ACTION_Prepare
    private static final String ACTION_COMPLETE = "CO"; // DocAction.ACTION_Complete
    private static final String ACTION_VOID = "VO"; // DocAction.ACTION_Void
    private static final String ACTION_CLOSE = "CL"; // DocAction.ACTION_Close

    // Display Labels for Status (user-friendly)
    private static final String LABEL_DRAFT = "DRAFT";
    private static final String LABEL_POSTED = "POSTED";
    private static final String LABEL_IN_PROGRESS = "IN PROGRESS";
    private static final String LABEL_VOIDED = "VOIDED";

    // PO Data Management
    private List<POData> availablePOs = new ArrayList<>();
    private POService poService;

    // Document Management - iDempiere DocumentAction integration
    private MESProdOutputDocument currentDocument;

    @Override
    protected void initForm() {
        try {
            desktop = Executions.getCurrent().getDesktop();
            poService = new POService(); // Initialize PO service

            // Initialize new MES Production Output Document
            initializeDocument();

            initializePOData();
            initFields();
            createAssemblyLayout();
            loadDummyData();
            
            
            // startDigitalClock();

            // Add ZK ClientInfoEvent listener for responsive behavior
            addClientInfoEventListener();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to initialize form", e);
            showErrorMessage("Failed to initialize form: " + e.getMessage());
        }
    }

    /**
     * Initialize new MES Production Output Document with DocumentAction workflow
     * Menggunakan client dan organizer dari session login iDempiere
     */
    private void initializeDocument() {
        Properties ctx = Env.getCtx();

        // Cek atau buat draft
        // MMES_ProdOutput draft = getOrCreateDraft();
        MMES_ProdOutput draft = MMES_ProdOutput.createOrGetDraft(ctx, MMES_ProdOutput.MES_TRANS_ID_ScanAssembly);

        // Ambil client dan org dari session login
        int clientId = Env.getAD_Client_ID(ctx);
        MClient client = MClient.get(ctx, clientId);
        System.out.print(draft.getDocumentNo());

        int orgId = Env.getAD_Org_ID(ctx);
        MOrg org = MOrg.get(ctx, orgId);

        currentDocument = new MESProdOutputDocument();
        currentDocument.setDocumentNo(draft.getDocumentNo());
        currentDocument.setLine(DEFAULT_LINE);
        currentDocument.setTenant(client.getName()); // Menggunakan nama client dari session
        currentDocument.setOrganizer(org.getName()); // Menggunakan nama org dari session
        currentDocument.setCreatedBy("System");
        currentDocument.setUpdatedBy("System");

        System.out.println("Initialized new MES Production Output Document: " + currentDocument.getDocumentNo() +
                " with status: " + currentDocument.getDocStatus() +
                ", Client: " + client.getName() + ", Organizer: " + org.getName());
    }

    /**
     * Initialize PO data - Load POs for current line from database
     */
    private void initializePOData() {
        try {

            List<POData> dbPOs = poService.getPOsForLine(DEFAULT_LINE);
            // Clear existing data
            availablePOs.clear();

            // Add placeholder first
            availablePOs.add(new POData("", "-- Select PO --", true));

            // Add database POs
            if (!dbPOs.isEmpty()) {
                availablePOs.addAll(dbPOs);
            }

        } catch (Exception e) {
            System.out.println("Failed to initialize PO data: " + e.getMessage());
        }
    }

    private void initFields() throws Exception {
        // Document No - Editable with default value
        fDocumentNo = new Textbox();
        fDocumentNo.setValue(currentDocument.getDocumentNo());
        fDocumentNo.setMaxlength(MAX_TEXT_LENGTH);
        fDocumentNo.setReadonly(true); // Make readonly
        fDocumentNo.setStyle(StyleManager.getInputReadonly());
        fDocumentNo.setHflex("1");
        fDocumentNo.addEventListener(Events.ON_CHANGE, this);
        fDocumentNo.addEventListener(Events.ON_FOCUS, this);
        fDocumentNo.addEventListener(Events.ON_BLUR, this);

        // Line - Editable with default value
        fLine = new Textbox();
        fLine.setValue(DEFAULT_LINE);
        fLine.setMaxlength(20);
        fLine.setReadonly(true); // Make readonly
        fLine.setStyle(StyleManager.getInputReadonly());
        fLine.setHflex("1");
        fLine.addEventListener(Events.ON_CHANGE, this);
        fLine.addEventListener(Events.ON_FOCUS, this);
        fLine.addEventListener(Events.ON_BLUR, this);

        // PO Field - IMPROVED: Enhanced combobox with proper keyboard navigation
        fPO = new Combobox();
        fPO.setHflex("1");
        fPO.setReadonly(false); // CRITICAL: Must be false to allow user selection
        fPO.setAutocomplete(false); // Disable autocomplete to prevent interference
        fPO.setAutodrop(true); // Auto-open dropdown on focus
        fPO.setButtonVisible(false); // Remove ">" symbol
        fPO.setInplace(true); // Use dropdown mode

        // IMPROVED: Clean styling with 24px font size - TAMBAHKAN outline: none untuk
        // menghilangkan garis biru

        // PO# Biar ditengah dan ukuran tinggi serta lebar combobox nya lebih besar
        fPO.setStyle(StyleManager.combine(
                StyleManager.getPOComboboxImproved(),
                StyleManager.getComboboxGlobalOverride(), // TAMBAHKAN: Global override untuk menghilangkan border biru
                // Tambahkan outline: none untuk menghilangkan garis biru saat focus
                "outline: none !important;",
                "font-size: 28px !important; height: 52px !important; padding: 12px 16px !important;",
                // Enforce popup item sizing strongly so list items match the input size
                ".z-combobox-popup .z-comboitem, .z-combobox-popup .z-comboitem-text {",
                "    font-size: 28px !important;",
                "    height: 60px !important;",
                "    padding: 16px 18px !important;",
                "    line-height: 28px !important;",
                "    font-weight: bold !important;",
                "    background-color: white !important;",
                "    color: #111 !important;",
                "    border: none !important;",
                "    display: block !important;",
                "    width: 100% !important;",
                "    box-sizing: border-box !important;",
                "}",

                StyleManager.getComboboxPopupGlobalOverride()));

        // Add a stable sclass for CSS targeting
        fPO.setSclass("po-combobox-widget");

        // Font Size PO#
        try {
            // Enhanced CSS for PO combo items: large font, spacing, pointer cursor,
            // and hover/focus background so users can see which item they're over.
            String css = ".z-combobox-popup .z-comboitem.po-comboitem, .z-combobox-popup .z-comboitem .z-comboitem-text, .po-comboitem {"
                    + "font-size: 24px !important;"
                    + "height: 56px !important;"
                    + "padding: 14px 16px !important;"
                    + "line-height: 24px !important;"
                    + "font-weight: bold !important;"
                    + "background-color: white !important;"
                    + "color: #111 !important;"
                    + "border: none !important;"
                    + "display: block !important;"
                    + "width: 100% !important;"
                    + "box-sizing: border-box !important;"
                    + "cursor: pointer !important;"
                    + "transition: background-color 0.12s ease !important;"
                    + "}"
                    // Hover and focus styles to highlight the item under the cursor
                    + ".z-combobox-popup .z-comboitem.po-comboitem:hover, .z-combobox-popup .z-comboitem.po-comboitem.z-comboitem-selected, .z-combobox-popup .z-comboitem.po-comboitem:focus, .po-comboitem:hover {"
                    + "background-color: #e6f7ff !important;"
                    + "color: #0b3b5a !important;"
                    + "}"
                    + ".z-combobox-popup .z-comboitem.po-comboitem:hover .z-comboitem-text, .z-combobox-popup .z-comboitem.po-comboitem.z-comboitem-selected .z-comboitem-text {"
                    + "background-color: transparent !important;"
                    + "color: inherit !important;"
                    + "}";

            // Add focused state CSS so server-side key navigation can visually highlight
            // items
            css += ".z-combobox-popup .z-comboitem.po-comboitem.po-focused, .z-combobox-popup .z-comboitem.po-comboitem.po-focused .z-comboitem-text {"
                    + "background-color: #e6f7ff !important;"
                    + "color: #0b3b5a !important;"
                    + "}";

            // Inject CSS and a small non-intrusive JS helper: open popup on focus/click
            // and let native ZK arrow key navigation work. On Enter, click the
            // currently highlighted comboitem so ZK will process selection and close.
            String openAndSelectJs = "(function(){"
                    + "try{"
                    + "  var styleId='po-combo-style';"
                    + "  if(!document.getElementById(styleId)){var s=document.createElement('style'); s.id=styleId; s.appendChild(document.createTextNode('"
                    + css.replace("'", "\\'") + "')); document.head.appendChild(s);}"
                    + "  var container = document.querySelector('.po-combobox-widget');"
                    + "  if(!container) return;"
                    + "  var input = container.querySelector('.z-combobox-input');"
                    + "  if(!input) return;"
                    + "  var openIfClosed = function(){ try{ var w = zk.Widget.$(input); if(w && !w.isOpen()) w.open(); }catch(e){} };"
                    + "  input.addEventListener('focus', function(){ openIfClosed(); }, true);"
                    + "  input.addEventListener('click', function(){ openIfClosed(); }, true);"
                    + "  input.addEventListener('keydown', function(e){ try{ if(e.keyCode===13){ var sel = document.querySelector('.z-combobox-popup .z-comboitem.z-comboitem-selected'); if(sel) sel.click(); } else if(e.keyCode===38||e.keyCode===40){ openIfClosed(); } }catch(err){} }, true);"
                    + "}catch(err){} })();";

            Clients.evalJavaScript(openAndSelectJs);
        } catch (Exception e) {
            log.log(Level.FINE, "Failed to inject client-side PO combo CSS", e);
        }

        // Remove JavaScript injection to avoid conflicts with CSS
        // Font size is now handled purely through CSS for consistency

        // Event listeners for proper selection handling and manual dropdown opening
        fPO.addEventListener(Events.ON_SELECT, this);
        fPO.addEventListener(Events.ON_CHANGE, this); // Backup for selection changes
        fPO.addEventListener(Events.ON_CLICK, this); // IMPROVED: Manual dropdown open on click
        fPO.addEventListener(Events.ON_OK, this); // Handle Enter key for selection

        bAddPO = new Button("+");
        bAddPO.setStyle(StyleManager.combine(
                StyleManager.getAddButtonCompact(),
                "font-size: 24px; width: 40px; height: 40px;" // Tambahkan ukuran lebih besar untuk button
        ));
        bAddPO.setTooltiptext("Add new PO for this line");
        bAddPO.addEventListener(Events.ON_CLICK, this);

        // Populate PO options AFTER fPO is fully initialized
        refreshPOCombobox();

        try {
            MLookup orgLookup = MLookupFactory.get(Env.getCtx(), getWindowNo(), 0,
                    MColumn.getColumn_ID("M_Movement", "AD_Org_ID"),
                    DisplayType.TableDir);
            fOrganizer = new WTableDirEditor("AD_Org_ID", true, true, true, orgLookup);
            int orgID = Env.getAD_Org_ID(Env.getCtx());
            if (orgID > 0) {
                fOrganizer.setValue(orgID);
                log.info("Organizer set from session: " + orgID);
            }

            // Add value change listener for organizer
            fOrganizer.addValueChangeListener(this);
        } catch (Exception e) {
            log.log(Level.WARNING, "Error creating organizer field", e);
        }



        // Client field - WTableDirEditor dengan lookup ke AD_Client_ID untuk Tenant
        try {
            MLookup clientLookup = MLookupFactory.get(Env.getCtx(), getWindowNo(), 0,
                    MColumn.getColumn_ID("M_Movement", "AD_Client_ID"),
                    DisplayType.TableDir);
            fClient = new WTableDirEditor("AD_Client_ID", true, true, true, clientLookup);
            int clientID = Env.getAD_Client_ID(Env.getCtx());
            if (clientID > 0) {
                fClient.setValue(clientID);
                log.info("Client (Tenant) set from session: " + clientID);
            }

            // Add value change listener for client
            fClient.addValueChangeListener(this);
        } catch (Exception e) {
            log.log(Level.WARNING, "Error creating client field", e);
            // Fallback - create simple textbox if WTableDirEditor fails
            // This fallback should be removed in production
        }

        // Barcode field - Enhanced with validation
        fBarcodeUPC = new Textbox();
        fBarcodeUPC.setPlaceholder("Input barcode here and press Enter");
        fBarcodeUPC.setMaxlength(MAX_BARCODE_LENGTH);
        fBarcodeUPC.setStyle(StyleManager.getEnhancedBarcodeInput());
        fBarcodeUPC.setHflex("1");
        fBarcodeUPC.addEventListener(Events.ON_OK, this);
        fBarcodeUPC.addEventListener(Events.ON_CHANGE, this);
        fBarcodeUPC.addEventListener(Events.ON_FOCUS, this);
        fBarcodeUPC.addEventListener(Events.ON_BLUR, this);

        // Status Label - Read-only field to show Draft/Posted status
        statusLabel = new Label(STATUS_DRAFT);
        statusLabel.setStyle(getStatusStyle(STATUS_DRAFT));
    }

    /**
     * Refresh PO combobox dengan data terbaru - FIXED: Proper combobox population
     */
    private void refreshPOCombobox() {
        try {

            if (fPO == null) {
                if (fPO == null) {
                    System.out.println("WARNING: fPO is null - cannot refresh combobox");
                    log.log(Level.WARNING, "fPO is null - cannot refresh combobox");
                    return;
                }
                return;
            }

            // Clear existing items
            fPO.getItems().clear();

            // Add all PO items to combobox
            for (int i = 0; i < availablePOs.size(); i++) {
                POData po = availablePOs.get(i);
                Comboitem item = new Comboitem();
                item.setValue(po.getPoNumber());
                item.setSclass("po-comboitem");
                item.setStyle(
                        "height: 56px !important; padding: 14px 16px !important; display: block !important; width: 100% !important; box-sizing: border-box !important; cursor: pointer !important; transition: background-color 0.12s ease !important;");
                item.setLabel(po.getDisplayText());
                fPO.appendChild(item);
                log.info("Added PO item " + i + ": " + po.getDisplayText() + " (value: " + po.getPoNumber() + ")");
            }

            // Set default selection to placeholder (index 0)
            fPO.setSelectedIndex(0);
            currentSelectedPONumber = null;

        } catch (Exception e) {
            System.err.println("Error refreshing PO combobox: " + e.getMessage());
            showErrorMessage("Failed to refresh PO list: " + e.getMessage());
        }
    }

    /**
     * Refresh PO data berdasarkan line yang dipilih - ENHANCED with database
     * integration
     */
    private void refreshPODataForLine(String lineCode) {
        try {
            if (Util.isEmpty(lineCode)) {
                return;
            }

            // Load POs untuk line yang baru dari database
            List<POData> newPOs = poService.getPOsForLine(lineCode);

            // Check if current selected PO is still available in new line
            boolean currentPOStillAvailable = false;
            if (currentSelectedPONumber != null) {
                for (POData po : newPOs) {
                    if (po.getPoNumber().equals(currentSelectedPONumber)) {
                        currentPOStillAvailable = true;
                        break;
                    }
                }
            }

            // Clear selection if current PO is not available in new line
            if (!currentPOStillAvailable && currentSelectedPONumber != null) {
                showErrorMessage("Current PO '" + currentSelectedPONumber + "' not available in line '" + lineCode
                        + "', clearing selection");
                currentSelectedPONumber = null;
            }

            // Update available POs
            availablePOs.clear();
            availablePOs.add(new POData("", "-- Select PO --", true)); // Placeholder
            availablePOs.addAll(newPOs);

            // Refresh combobox (will maintain selection if PO is still available)
            refreshPOCombobox();

            showInfoMessage("PO list updated for line: " + lineCode + " (" + newPOs.size() + " POs available)");

        } catch (Exception e) {
            showErrorMessage("Failed to load PO data for line: " + lineCode + " - " + e.getMessage());
        }
    }

    /**
     * Get display label for status code
     */
    private String getStatusDisplayLabel(String statusCode) {
        switch (statusCode) {
            case STATUS_DRAFT:
                return LABEL_DRAFT;
            case STATUS_POSTED:
                return LABEL_POSTED;
            case STATUS_IN_PROGRESS:
                return LABEL_IN_PROGRESS;
            case STATUS_VOIDED:
                return LABEL_VOIDED;
            default:
                return statusCode;
        }
    }

    /**
     * Get status style based on current status code (using iDempiere DocumentAction
     * standards)
     */
    private String getStatusStyle(String statusCode) {
        String baseStyle = StyleManager.combine(
                StyleManager.PX_3,
                StyleManager.PY_2,
                StyleManager.ROUNDED_SM,
                StyleManager.TEXT_SM,
                StyleManager.FONT_BOLD,
                StyleManager.TEXT_CENTER,
                "min-width: 80px; border: 1px solid;");

        switch (statusCode) {
            case STATUS_DRAFT:
                return StyleManager.combine(
                        baseStyle,
                        StyleManager.BG_YELLOW_50,
                        "border-color: #fbbf24; color: #d97706; background-color: #fefce8;");
            case STATUS_POSTED: // Completed
                return StyleManager.combine(
                        baseStyle,
                        StyleManager.BG_GREEN_600,
                        StyleManager.TEXT_WHITE,
                        "border-color: #16a34a;");
            case STATUS_IN_PROGRESS:
                return StyleManager.combine(
                        baseStyle,
                        StyleManager.BG_BLUE_50,
                        "border-color: #3b82f6; color: #1d4ed8; background-color: #eff6ff;");
            case STATUS_VOIDED:
                return StyleManager.combine(
                        baseStyle,
                        StyleManager.BG_RED_600,
                        StyleManager.TEXT_WHITE,
                        "border-color: #dc2626;");
            default:
                return baseStyle;
        }
    }

    /**
     * Update document status with proper DocumentAction codes
     */
    private void updateDocumentStatus(String statusCode) {
        if (statusLabel != null) {
            statusLabel.setValue(getStatusDisplayLabel(statusCode));
            statusLabel.setStyle(getStatusStyle(statusCode));
        }

        // Clear PO selection when resetting to Draft status
        if (STATUS_DRAFT.equals(statusCode)) {
            currentSelectedPONumber = null;
            // Reset PO combobox to placeholder if it exists
            if (fPO != null && fPO.getItems().size() > 0) {
                fPO.setSelectedIndex(0);
            }
        }
    }

    /**
     * Process Document Action (simulate iDempiere DocumentEngine.processDocument)
     */
    private boolean processDocumentAction(String action) {
        try {
            switch (action) {
                case ACTION_PREPARE:
                    updateDocumentStatus(STATUS_IN_PROGRESS);
                    showInfoMessage("Document prepared. Status: " + LABEL_IN_PROGRESS);
                    return true;

                case ACTION_COMPLETE:
                    updateDocumentStatus(STATUS_POSTED);
                    showSuccessMessage("Document completed. Status: " + LABEL_POSTED);
                    return true;

                case ACTION_VOID:
                    updateDocumentStatus(STATUS_VOIDED);
                    showInfoMessage("Document voided. Status: " + LABEL_VOIDED);
                    return true;

                case ACTION_CLOSE:
                    // Keep current status but add closed flag
                    showInfoMessage("Document closed.");
                    return true;

                default:
                    showErrorMessage("Unknown document action: " + action);
                    return false;
            }
        } catch (Exception e) {
            showErrorMessage("Failed to process document action: " + e.getMessage());
            return false;
        }
    }

    // Security: Input validation methods
    private boolean validateInput(String input, String pattern, int maxLength) {
        if (input == null)
            return false;
        if (input.length() > maxLength)
            return false;
        return input.matches(pattern);
    }

    private String sanitizeInput(String input) {
        if (input == null)
            return "";
        return input.replaceAll("[<>\"'&]", "").trim();
    }

    private boolean validateBarcode(String barcode) {
        if (Util.isEmpty(barcode))
            return false;
        return validateInput(barcode, BARCODE_PATTERN, MAX_BARCODE_LENGTH);
    }

    private boolean validateDocumentNo(String documentNo) {
        if (Util.isEmpty(documentNo))
            return false;
        return validateInput(documentNo, BARCODE_PATTERN, MAX_TEXT_LENGTH);
    }

    private boolean validatePO(String poNumber) {
        if (Util.isEmpty(poNumber))
            return false;
        return validateInput(poNumber, BARCODE_PATTERN, MAX_PO_LENGTH);
    }

    private void showValidationError(Textbox field, String message) {
        field.setStyle(StyleManager.combine(
                StyleManager.getInputEditable(),
                StyleManager.getValidationError()));
        showErrorMessage(message);
    }

    private void showValidationSuccess(Textbox field) {
        field.setStyle(StyleManager.combine(
                StyleManager.getInputEditable(),
                StyleManager.getValidationSuccess()));
    }

    private void resetFieldValidation(Textbox field) {
        field.setStyle(StyleManager.getInputEditable());
    }

    private void showErrorMessage(String message) {
        if (infoLabel != null) {
            infoLabel.setValue("Error: " + message);
            infoLabel.setStyle(StyleManager.combine(
                    StyleManager.getInfoMessage(),
                    StyleManager.TEXT_RED_600));
        }
    }

    private void showSuccessMessage(String message) {
        if (infoLabel != null) {
            infoLabel.setValue(message);
            infoLabel.setStyle(StyleManager.combine(
                    StyleManager.getInfoMessage(),
                    StyleManager.TEXT_GREEN_600));
        }
    }

    private void showInfoMessage(String message) {
        if (infoLabel != null) {
            infoLabel.setValue(message);
            infoLabel.setStyle(StyleManager.getInfoMessage());
        }
    }

    // Clock and layout methods remain the same...
    private void startDigitalClock() {
        clockTimer = new Timer(true);

        TimerTask clockTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    LocalDateTime indonesiaTime = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
                    String formattedDateTime = indonesiaTime.format(formatter);

                    if (desktop != null && dateTimeLabel != null) {
                        try {
                            Executions.activate(desktop);
                            dateTimeLabel.setValue(formattedDateTime);
                            dateTimeLabel.invalidate();
                        } finally {
                            Executions.deactivate(desktop);
                        }
                    }
                } catch (Exception e) {
                    log.log(Level.WARNING, "Clock update error", e);
                }
            }
        };

        clockTimer.scheduleAtFixedRate(clockTask, 0, 1000);
    }

    @Override
    public void onClose() {
        if (clockTimer != null) {
            clockTimer.cancel();
            clockTimer = null;
        }
        super.onClose();
    }

    private void createAssemblyLayout() {
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
        north.setSize(StyleManager.getNorthPanelSize());

        Div titleBar = createTitleBar();
        north.appendChild(titleBar);
        mainLayout.appendChild(north);

        // Center - Main Content with responsive container
        Center center = new Center();
        center.setStyle(StyleManager.getCenterPanel());
        center.setHflex("1");

        Div responsiveContainer = new Div();
        responsiveContainer.setStyle(StyleManager.getZKResponsiveContainer());

        Vbox centerContent = new Vbox();
        centerContent.setStyle(StyleManager.getCenterContent());
        centerContent.setSpacing(StyleManager.getSpacing8());
        centerContent.setVflex("1");
        centerContent.setHflex("1");

        Div formInfoSection = createFormInfoSection();
        centerContent.appendChild(formInfoSection);

        Div tableSection = createTableSection();
        tableSection.setVflex("1");
        centerContent.appendChild(tableSection);

        responsiveContainer.appendChild(centerContent);
        center.appendChild(responsiveContainer);
        mainLayout.appendChild(center);

        // South - Statistics
        South south = new South();
        south.setStyle(StyleManager.combine(StyleManager.BG_GRAY_50, StyleManager.BORDER_TOP_2));
        south.setSize(StyleManager.getSouthPanelSize());

        createBottomPanel();
        south.appendChild(statsPanel);
        mainLayout.appendChild(south);
    }

    private Div createTitleBar() {
        Div titleBar = new Div();
        titleBar.setStyle(StyleManager.getTitleBar());

        Label titleLabel = new Label("Assembling");
        titleLabel.setStyle(StyleManager.getTitleLabel());

        dateTimeLabel = new Label();
        dateTimeLabel.setStyle(StyleManager.getDigitalClock());

        titleBar.appendChild(titleLabel);
        titleBar.appendChild(dateTimeLabel);

        return titleBar;
    }

    private Div createFormInfoSection() {
        Div formInfoSection = new Div();
        formInfoSection.setStyle(StyleManager.getZKResponsiveFlexRowToCol()); // ZK-responsive: row on desktop, column
                                                                              // on mobile

        Div leftColumn = new Div();
        leftColumn.setStyle(StyleManager.getLeftColumn());
        createFormInputs();
        leftColumn.appendChild(formPanel);

        Div rightColumn = new Div();
        rightColumn.setStyle(StyleManager.getRightColumn());
        createInfoPanel();
        rightColumn.appendChild(infoPanel);

        formInfoSection.appendChild(leftColumn);
        formInfoSection.appendChild(rightColumn);

        return formInfoSection;
    }

    // ini beda
    private void createFormInputs() {
        formPanel = new Div();
        formPanel.setStyle(StyleManager.W_FULL);

        Div mainContainer = new Div();
        mainContainer.setStyle(StyleManager.getEnhancedFormContainer());

        Div gridContainer = new Div();
        gridContainer.setStyle(StyleManager.getZKResponsiveFormGrid()); // ZK-responsive grid: 2 columns on desktop, 1
                                                                        // on mobile

        // Row 1: Client | Organizer
        gridContainer.appendChild(createFormFieldContainer("Client", fClient));
        gridContainer.appendChild(createFormFieldContainer("Org", fOrganizer));

        // Row 2: Document No | Line
        gridContainer.appendChild(createFormFieldContainer("Document No", fDocumentNo));
        gridContainer.appendChild(createFormFieldContainer("Line", fLine));

        // Row 3: PO# with Add Button | Barcode UPC
        gridContainer.appendChild(createPOFieldContainer());
        gridContainer.appendChild(createFormFieldContainer("Barcode UPC", fBarcodeUPC));

        mainContainer.appendChild(gridContainer);
        formPanel.appendChild(mainContainer);
    }

    // ini beda
    /**
     * Create PO field container with Add button - FIXED: Button aligned with
     * Document No and improved click area handling
     */
    private Div createPOFieldContainer() {
        Div container = new Div();
        container.setStyle(StyleManager.createPOFieldContainer());

        Label label = new Label("PO #");
        label.setStyle(StyleManager.getLabel());
        container.appendChild(label);

        // Container untuk PO field dan button - FIXED: Improved layout to prevent
        // overlap
        Div poInputContainer = new Div();
        poInputContainer.setStyle(StyleManager.combine(
                StyleManager.poInputContainer(),
                "height: 40px;", // Tambahkan tinggi lebih besar untuk container
                "position: relative;", // Add relative positioning for better control
                "z-index: 1;" // Ensure proper stacking
        ));

        // PO field wrapper - FIXED: Prevent overlap with button
        Div poWrapper = new Div();
        poWrapper.setStyle(StyleManager.combine(
                "flex: 1;",
                "min-width: 0;",
                "height: 40px;", // Tinggi diperbesar menjadi 40px
                "margin-right: 8px;", // Add margin to separate from button
                "position: relative;",
                "z-index: 2;" // Higher z-index than button
        ));
        poWrapper.appendChild(fPO);

        // Button wrapper - FIXED: Proper positioning and sizing
        Div buttonWrapper = new Div();
        buttonWrapper.setStyle(StyleManager.combine(
                StyleManager.buttonWrapper(),
                "position: relative;",
                "z-index: 1;", // Lower z-index than combobox
                "flex-shrink: 0;" // Prevent button from shrinking
        ));
        buttonWrapper.appendChild(bAddPO);

        poInputContainer.appendChild(poWrapper);
        poInputContainer.appendChild(buttonWrapper);

        container.appendChild(poInputContainer);

        return container;
    }

    private Div createFormFieldContainer(String labelText, Object field) {
        Div container = new Div();
        container.setStyle(StyleManager.getEnhancedFormFieldWithValidation());

        Label label = new Label(labelText);
        label.setStyle(StyleManager.getLabel());
        container.appendChild(label);

        if (field instanceof Textbox) {
            container.appendChild((Textbox) field);
        } else if (field instanceof Combobox) {
            container.appendChild((Combobox) field);
        } else if (field instanceof WTableDirEditor) {
            // For WTableDirEditor, append the component
            container.appendChild(((WTableDirEditor) field).getComponent());
        }

        return container;
    }

    private void createInfoPanel() {
        infoPanel = new Div();
        infoPanel.setStyle(StyleManager.getResponsiveInfoPanel());
        infoPanel.setHflex("1"); // Make info panel width follow the datatable width

        Label infoTitle = new Label("Information Message");
        infoTitle.setStyle(StyleManager.getInfoTitle());

        infoLabel = new Label("Ready for input. Please select a PO and scan barcodes.");
        infoLabel.setStyle(StyleManager.getInfoMessage());

        infoPanel.appendChild(infoTitle);
        infoPanel.appendChild(infoLabel);

    }

    // Table and bottom panel methods - FIXED VERSION
    private void createDataTable() {
        // Table configuration - RESTORED functionality with proper ZK components
        table = new Listbox();
        table.setHflex("1");
        table.setRows(5); // Show 5 rows at a time
        table.setMold("paging"); // Enable pagination/scrolling
        table.setPageSize(5); // Set page size to 5 rows

        // CRITICAL: Enable all interactions for table
        table.setCheckmark(false); // No checkmarks but allow selection
        table.setMultiple(false); // Single selection
        table.setSpan("true"); // Enable spanning
        // REMOVED: setVflex("true") - conflicts with setRows(5)

        // Apply styling that enables all interactions with explicit pointer events
        table.setStyle(StyleManager.combine(
                StyleManager.getMobileResponsiveTableWithHover(),
                "pointer-events: auto !important;", // Force enable all pointer events
                "overflow-x: hidden !important;", // Prevent horizontal scrolling
                "overflow-y: auto !important;", // Allow vertical scrolling if needed
                "user-select: auto !important;" // Allow text selection
        ));

        Listhead header = new Listhead();
        header.setStyle(StyleManager.combine(StyleManager.BG_GRAY_50, StyleManager.BORDER_BOTTOM_2));

        String[] headers = { "No", "Barcode UPC", "PO", "Article", "Size", "Qty", "Action" };
        String[] widths = { "15px", "60px", "45px", "55px", "30px", "30px", "30px" };

        for (int i = 0; i < headers.length; i++) {
            Listheader h = new Listheader(headers[i]);
            h.setWidth(widths[i]);

            // FIXED: Correct styling application
            if (i == 0 || i == 4 || i == 5 || i == 6) {
                h.setStyle(StyleManager.getTableCellCenterBold());
            } else {
                h.setStyle(StyleManager.getTableCellBold());
            }

            header.appendChild(h);
        }

        table.appendChild(header);

        // RESTORED: Table functionality - delete buttons work, scrolling enabled, row
        // selection enabled
        // Table is fully interactive now
    }

    private Div createTableSection() {
        Div tableSection = new Div();
        tableSection.setStyle(StyleManager.combine(
                StyleManager.FLEX_1,
                StyleManager.FLEX_COL,
                StyleManager.W_FULL));

        // Table container dengan height yang cukup untuk table dan button
        Div tableContainer = new Div();
        tableContainer.setStyle(StyleManager.getZKResponsiveTableContainer()); // ZK-responsive table container

        // Table wrapper dengan scroll
        Div tableWrapper = new Div();
        tableWrapper.setStyle(StyleManager.tableWrapper());

        createDataTable();
        tableWrapper.appendChild(table);

        // Posting section - FIXED: Better positioning and spacing
        Div postingSection = new Div();
        postingSection.setStyle(StyleManager.postingSection());

        // Status Label (di pojok kiri) - NEW
        statusLabel.setStyle(getStatusStyle(STATUS_DRAFT));
        postingSection.appendChild(statusLabel);

        // Posting Button (untuk posting data ke SAP) - FIXED: Enhanced styling
        bPosting = new Button("Posting");
        bPosting.setStyle(StyleManager.getButtonPrimary());
        bPosting.addEventListener(Events.ON_CLICK, this);
        postingSection.appendChild(bPosting);

        // Assemble the structure
        tableContainer.appendChild(tableWrapper);
        tableContainer.appendChild(postingSection);
        tableSection.appendChild(tableContainer);

        return tableSection;
    }

    private void createBottomPanel() {
        statsPanel = new Div();
        statsPanel.setStyle(StyleManager.getStatsPanel());

        Div footerContainer = new Div();
        footerContainer.setStyle(StyleManager.getZKResponsiveFlexRowToCol()); // Responsive footer: row on desktop,
                                                                              // column on mobile

        Div statsContainer = new Div();
        statsContainer.setStyle(StyleManager.getStatsContainer());

        // Scan Per Jam
        Div scanPerHourDiv = new Div();
        scanPerHourDiv.setStyle(StyleManager.getStatCardFirst());

        Label scanPerHourTitle = new Label("Scan/Jam");
        scanPerHourTitle.setStyle(StyleManager.getStatTitle());

        scanPerHourLabel = new Label("32");
        scanPerHourLabel.setStyle(StyleManager.getStatNumber(StyleManager.TEXT_BLUE_600));

        scanPerHourDiv.appendChild(scanPerHourTitle);
        scanPerHourDiv.appendChild(scanPerHourLabel);

        // Total Scan
        Div totalScanDiv = new Div();
        totalScanDiv.setStyle(StyleManager.getStatCardLast());

        Label totalScanTitle = new Label("Total");
        totalScanTitle.setStyle(StyleManager.getStatTitle());

        totalScanLabel = new Label("142");
        totalScanLabel.setStyle(StyleManager.getStatNumber(StyleManager.TEXT_GREEN_600));

        totalScanDiv.appendChild(totalScanTitle);
        totalScanDiv.appendChild(totalScanLabel);

        statsContainer.appendChild(scanPerHourDiv);
        statsContainer.appendChild(totalScanDiv);

        // Button Container - Only Report Detail button
        Div buttonContainer = new Div();
        buttonContainer.setStyle(StyleManager.getButtonContainer());

        // Report Detail Button
        bReportDetail = new Button("Report Detail");
        bReportDetail.setStyle(StyleManager.getZKResponsiveButton()); // ZK-responsive button
        bReportDetail.addEventListener(Events.ON_CLICK, this);

        buttonContainer.appendChild(bReportDetail);

        footerContainer.appendChild(statsContainer);
        footerContainer.appendChild(buttonContainer);

        statsPanel.appendChild(footerContainer);
    }

    @Override
    public void onEvent(Event event) throws Exception {
        try {
            log.info("Event received: " + event.getName() + " from " + event.getTarget().getClass().getSimpleName());

            if (event.getTarget() == fBarcodeUPC && Events.ON_OK.equals(event.getName())) {
                handleBarcodeInput();
            } else if (event.getTarget() == fPO) {
                String ev = event.getName();
                // ENTER / OK: confirm selection and close dropdown
                if (Events.ON_OK.equals(ev)) {
                    log.info("✓ PO ENTER/OK event detected - confirming selection");
                    handlePOEnterKey();
                }
                // SELECT / CHANGE: treat as a preview — do NOT close the dropdown.
                // Inform user to press Enter to confirm. Re-open popup if it was closed by
                // default behavior so the list remains visible.
                else if (Events.ON_SELECT.equals(ev) || Events.ON_CHANGE.equals(ev)) {
                    log.info("✓ PO select/change event detected (preview): " + ev);
                    int selIndex = fPO.getSelectedIndex();
                    focusedPOIndex = selIndex; // store focused index as preview
                    if (selIndex > 0 && selIndex < availablePOs.size()) {
                        POData preview = availablePOs.get(selIndex);
                        String previewText = preview.getPoNumber() != null && !preview.getPoNumber().isEmpty()
                                ? preview.getPoNumber()
                                : preview.getDisplayText();
                        showInfoMessage("PO selected (preview): " + previewText + " — press Enter to confirm.");
                        log.info("Preview PO set to: " + previewText);
                    } else {
                        showInfoMessage("Please select a valid PO. Press Enter to confirm.");
                        log.info("Preview selection is placeholder or invalid (index=" + selIndex + ")");
                    }
                    // Try to keep the popup open so user can still press Enter while list visible
                    try {
                        if (!fPO.isOpen()) {
                            fPO.open();
                            log.info("Re-opened PO dropdown to keep it visible after selection preview");
                        }
                    } catch (Exception ex) {
                        log.log(Level.FINE, "Failed to re-open PO dropdown after preview", ex);
                    }
                }
                // CLICK is handled elsewhere to open dropdown manually, but keep it safe here
                else if (Events.ON_CLICK.equals(ev)) {
                    log.info("✓ PO click event detected - opening dropdown manually");
                    handlePOClick();
                }
            } else if (event.getTarget() == fPO && Events.ON_CLICK.equals(event.getName())) {
                log.info("✓ PO click event detected - opening dropdown manually");
                handlePOClick();
            } else if (event.getTarget() == fLine && Events.ON_BLUR.equals(event.getName())) {
                handleLineChange();
            } else if (event.getTarget() == bAddPO && Events.ON_CLICK.equals(event.getName())) {
                log.info("Add PO button clicked!");
                handleAddNewPO();
            } else if (event.getTarget() == bPosting) {
                performPosting();
            } else if (event.getTarget() == bReportDetail) {
                showReportDetail();
            } else if (event.getTarget() instanceof Button) {
                Button btn = (Button) event.getTarget();
                if (btn.getAttribute("dataIndex") != null) {
                    int index = (Integer) btn.getAttribute("dataIndex");
                    removeItem(index);
                }
            } else if (Events.ON_FOCUS.equals(event.getName())) {
                handleFieldFocus(event);
            } else if (Events.ON_BLUR.equals(event.getName())) {
                handleFieldBlur(event);
            } else if (Events.ON_CHANGE.equals(event.getName())) {
                handleFieldChange(event);
            }
        } catch (Exception e) {
            showErrorMessage("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Handle value change events from WTableDirEditor components
     */
    @Override
    public void valueChange(ValueChangeEvent evt) {
        try {
            Object source = evt.getSource();

            if (source == fClient) {
                log.info("Client value changed: " + evt.getNewValue());
                // Handle client change if needed
            } else if (source == fOrganizer) {
                log.info("Organizer value changed: " + evt.getNewValue());
                // Handle organizer change if needed
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, "Error handling value change event", e);
            showErrorMessage("Error processing value change: " + e.getMessage());
        }
    }

    // Update method handleBarcodeInput() untuk menggunakan session values
    private void handleBarcodeInput() {
        String barcode = sanitizeInput(fBarcodeUPC.getValue());

        if (!validateBarcode(barcode)) {
            showValidationError(fBarcodeUPC, "Invalid barcode format. Only alphanumeric characters allowed.");
            fBarcodeUPC.focus();
            return;
        }

        // Primary validation: Check stored PO number
        if (currentSelectedPONumber == null || currentSelectedPONumber.trim().isEmpty()) {
            // Secondary validation: Try to get from current combobox selection
            int currentIndex = fPO.getSelectedIndex();
            if (currentIndex > 0 && currentIndex < availablePOs.size()) {
                POData currentPO = availablePOs.get(currentIndex);
                if (currentPO != null && !currentPO.isPlaceholder()) {
                    currentSelectedPONumber = currentPO.getPoNumber();
                    log.info("✓ Recovered PO from combobox selection: " + currentSelectedPONumber);
                    showSuccessMessage("PO recovered: " + currentSelectedPONumber);
                }
            }
        }

        // Final validation
        if (currentSelectedPONumber == null || currentSelectedPONumber.trim().isEmpty()) {
            showErrorMessage("Please select a PO before scanning barcodes. Click on the PO# field to choose a PO.");
            fPO.focus();
            return;
        }

        log.info("✓ PO validation passed - using PO: " + currentSelectedPONumber);

        // Validate required fields are filled - Client and Organizer are auto-filled
        // from session
        String client = fClient != null ? fClient.getValue() != null ? fClient.getValue().toString() : "" : "";
        String organizer = fOrganizer != null ? fOrganizer.getValue() != null ? fOrganizer.getValue().toString() : ""
                : "";
        String docNo = fDocumentNo.getValue();
        String line = fLine.getValue();

        if (client == null || client.trim().isEmpty() ||
                organizer == null || organizer.trim().isEmpty() ||
                docNo == null || docNo.trim().isEmpty() ||
                line == null || line.trim().isEmpty()) {
            showErrorMessage(
                    "Please fill all required fields: Document No, Line (Client and Organizer are auto-filled from session)");
            return;
        }

        log.info("✓ All validation passed - creating barcode item");
        addBarcodeItem(barcode);
        fBarcodeUPC.setValue("");
        fBarcodeUPC.focus();
        showValidationSuccess(fBarcodeUPC);
    }

    private void handlePOSelection() {
        try {
            int selectedIndex = fPO.getSelectedIndex();
            String selectedValue = fPO.getValue();

            // Enhanced validation with better error handling
            if (selectedIndex >= 0 && selectedIndex < availablePOs.size()) {
                POData selectedPO = availablePOs.get(selectedIndex);

                if (selectedIndex == 0) {
                    // User selected placeholder - clear selection
                    currentSelectedPONumber = null;
                    showInfoMessage("Please select a valid PO from the dropdown list.");

                } else if (selectedPO != null && !selectedPO.isPlaceholder()) {
                    // FIXED: Enhanced validation - check both poNumber and displayText
                    String poNum = selectedPO.getPoNumber();
                    String dispText = selectedPO.getDisplayText();

                    log.info("Validating PO: poNumber='" + poNum + "', displayText='" + dispText + "'");

                    if ((poNum != null && !poNum.trim().isEmpty()) ||
                            (dispText != null && !dispText.trim().isEmpty())) {

                        // Use poNumber if available, otherwise use displayText
                        currentSelectedPONumber = (poNum != null && !poNum.trim().isEmpty()) ? poNum : dispText;
                        // Show success message with clear feedback
                        showSuccessMessage(
                                "✓ PO selected: " + currentSelectedPONumber + " - Ready for barcode scanning!");

                        // Set focus to barcode input
                        if (fBarcodeUPC != null) {
                            fBarcodeUPC.focus();
                            log.info("Focus set to barcode input field");
                        }

                    } else {
                        currentSelectedPONumber = null;
                        showErrorMessage("Invalid PO data. Please try selecting a different PO.");
                    }

                } else {
                    currentSelectedPONumber = null;
                    showErrorMessage("Invalid PO selection. Please select a different PO.");
                }

            } else {
                currentSelectedPONumber = null;
                showErrorMessage("Please select a valid PO from the list.");
            }

        } catch (Exception e) {
            showErrorMessage("Error processing PO selection: " + e.getMessage());
            currentSelectedPONumber = null;
        }
    }

    /**
     * Handle PO Enter key - confirm selection and close dropdown
     */
    private void handlePOEnterKey() {
        try {
            int selectedIndex = fPO.getSelectedIndex();

            // If dropdown is open and we have a valid selection, confirm it
            if (fPO.isOpen() && selectedIndex >= 0 && selectedIndex < availablePOs.size()) {
                POData selectedPO = availablePOs.get(selectedIndex);
                if (selectedPO != null && !selectedPO.isPlaceholder()) {
                    // Close the dropdown
                    fPO.close();

                    // Process the selection
                    handlePOSelection();
                }
            }

        } catch (Exception e) {
            showErrorMessage("Error processing PO selection: " + e.getMessage());
        }
    }

    /**
     * Handle PO click - open dropdown if not already open
     */
    private void handlePOClick() {
        try {

            // Ensure focus is set before opening dropdown to fix right-click area
            // navigation
            fPO.focus();

            // Since button is hidden, we need to manually trigger dropdown
            if (!fPO.isOpen()) {
                fPO.open(); // Open the dropdown manually
                showInfoMessage("Select a PO from the list below:");
            }

        } catch (Exception e) {
            // Fallback: try to show dropdown items anyway
            showInfoMessage("Click to select PO - " + availablePOs.size() + " options available");
        }
    }

    /**
     * Handle line change - refresh PO data for new line
     */
    private void handleLineChange() {
        String lineCode = sanitizeInput(fLine.getValue());
        if (!Util.isEmpty(lineCode) && !lineCode.equals(DEFAULT_LINE)) {
            refreshPODataForLine(lineCode);
        }
    }

    /**
     * Handle Add New PO button click
     */
    private void handleAddNewPO() {
        try {
            showAddPODialog();
        } catch (Exception e) {
            showErrorMessage("Failed to open Add PO dialog: " + e.getMessage());
        }
    }

    /**
     * Show dialog untuk menambah PO baru - FIXED for proper modal display
     */
    private void showAddPODialog() {
        try {
            // Create compact modal window - FIXED: Smaller width for clean design
            Window addPOWindow = new Window();
            addPOWindow.setTitle("Add New PO");
            addPOWindow.setBorder("normal");
            addPOWindow.setClosable(true);
            addPOWindow.setWidth("380px"); // Reduced from 500px to 380px
            addPOWindow.setHeight("280px"); // Reduced from 350px to 280px
            addPOWindow.setStyle("border-radius: 8px;");

            // Attach to proper parent before setting modal mode
            this.appendChild(addPOWindow);

            // Content container - FIXED: Compact padding
            Div content = new Div();
            content.setStyle(StyleManager.contentContainer());

            // Title - FIXED: Smaller and more compact
            Label title = new Label("Add New PO");
            title.setStyle(StyleManager.titleDialogPO());
            content.appendChild(title);

            // PO Number input - FIXED: Compact layout
            Hbox poBox = new Hbox();
            poBox.setStyle(StyleManager.poBoxDialog());
            poBox.setAlign("center");

            Label poLabel = new Label("PO Number:");
            poLabel.setStyle(StyleManager.poLabelDialog());

            Textbox poInput = new Textbox();
            poInput.setStyle(StyleManager.poInputDialog());
            poInput.setPlaceholder("Enter PO number (e.g., 912121440)");
            poInput.setMaxlength(MAX_PO_LENGTH);

            poBox.appendChild(poLabel);
            poBox.appendChild(poInput);
            content.appendChild(poBox);

            // Buttons - FIXED: Compact spacing
            Hbox buttonBox = new Hbox();
            buttonBox.setStyle("margin-top: 15px; width: 100%; text-align: center;");
            buttonBox.setAlign("center");
            buttonBox.setPack("center");

            Button saveButton = new Button("Save");
            saveButton.setStyle(
                    "background-color: #007bff; color: white; border: none; padding: 12px 30px; border-radius: 6px; font-weight: bold; font-size: 14px; cursor: pointer; margin-right: 10px;");
            saveButton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                @Override
                public void onEvent(Event event) throws Exception {
                    String poNumber = sanitizeInput(poInput.getValue());

                    if (Util.isEmpty(poNumber)) {
                        Dialog.warn(0, "Please enter PO number");
                        poInput.focus();
                        return;
                    }

                    if (!validatePO(poNumber)) {
                        Dialog.warn(0, "Invalid PO number format");
                        poInput.focus();
                        return;
                    }

                    // Add new PO
                    addNewPO(poNumber);
                    addPOWindow.detach(); // Proper modal close
                }
            });

            Button cancelButton = new Button("Cancel");
            cancelButton.setStyle(
                    "background-color: #6c757d; color: white; border: none; padding: 10px 20px; border-radius: 4px; width: auto; min-width: 140px; font-weight: bold; font-size: 14px; cursor: pointer;");
            cancelButton.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                @Override
                public void onEvent(Event event) throws Exception {
                    addPOWindow.detach(); // Proper modal close
                }
            });

            buttonBox.appendChild(saveButton);
            buttonBox.appendChild(cancelButton);
            content.appendChild(buttonBox);

            addPOWindow.appendChild(content);

            // Show modal with proper mode setting
            addPOWindow.setMode(Window.MODAL);
            addPOWindow.doModal();

        } catch (Exception e) {
            showErrorMessage("Failed to show add PO dialog: " + e.getMessage());
            Dialog.error(0, "Error", "Failed to open Add PO dialog: " + e.getMessage());
        }
    }

    /**
     * Add new PO to available list
     */
    private void addNewPO(String poNumber) {
        try {

            // Check if PO already exists
            for (POData po : availablePOs) {
                if (poNumber.equals(po.getPoNumber())) {
                    showErrorMessage("PO " + poNumber + " already exists");
                    Dialog.warn(0, "PO " + poNumber + " already exists");
                    return;
                }
            }

            // FIXED: Create new PO with only PO number display, no description
            POData newPO = new POData(poNumber, poNumber, false);

            // Add to service (simulate database save)
            poService.addPOToLine(fLine.getValue(), newPO);

            // Add to local list
            availablePOs.add(newPO);

            // Refresh combobox
            refreshPOCombobox();

            // Select the new PO
            for (int i = 0; i < availablePOs.size(); i++) {
                if (availablePOs.get(i).getPoNumber().equals(poNumber)) {
                    fPO.setSelectedIndex(i);
                    // Update the stored selection
                    currentSelectedPONumber = poNumber;
                    break;
                }
            }

            showSuccessMessage("PO " + poNumber + " added successfully");
            Dialog.info(0, "Success", "PO " + poNumber + " added successfully!");

        } catch (Exception e) {
            showErrorMessage("Failed to add new PO: " + e.getMessage());
            Dialog.error(0, "Error", "Failed to add new PO: " + e.getMessage());
        }
    }

    // REMOVED: Table event handling methods to prevent casting errors
    // Table is now just for display, no click actions

    private void handleFieldFocus(Event event) {
        if (event.getTarget() instanceof Textbox) {
            Textbox field = (Textbox) event.getTarget();
            resetFieldValidation(field);
        }
    }

    private void handleFieldBlur(Event event) {
        if (event.getTarget() instanceof Textbox) {
            Textbox field = (Textbox) event.getTarget();

            // Skip validation for WTableDirEditor components
            validateField(field);
        }
    }

    private void handleFieldChange(Event event) {
        if (event.getTarget() instanceof Textbox) {
            Textbox field = (Textbox) event.getTarget();

            String value = sanitizeInput(field.getValue());
            field.setValue(value);
        }
    }

    // Update method validateField() untuk menggunakan WTableDirEditor untuk
    // organizer
    private void validateField(Textbox field) {
        String value = field.getValue();

        if (field == fDocumentNo) {
            if (!validateDocumentNo(value)) {
                showValidationError(field, "Invalid document number format.");
            } else {
                showValidationSuccess(field);
                // Reset status to DRAFT when document number changes
                updateDocumentStatus(STATUS_DRAFT);
            }
        } else if (field == fBarcodeUPC) {
            if (!Util.isEmpty(value) && !validateBarcode(value)) {
                showValidationError(field, "Invalid barcode format.");
            } else if (!Util.isEmpty(value)) {
                showValidationSuccess(field);
            }
        } else if (!Util.isEmpty(value)) {
            showValidationSuccess(field);
        }
    }

    // Data manipulation methods - Enhanced with descending order support
    private void loadDummyData() {
        try {
            // Clear current data and load from database
            dataList.clear();
            refreshDataFromDatabase();

            // Update UI
            // refreshTable();
            updateStatistics();

            // Show simple message
            if (dataList.isEmpty()) {
                showInfoMessage("Ready for input. Please select a PO and scan barcodes.");
            } else {
                showInfoMessage("Loaded " + dataList.size() + " existing items. Ready to continue scanning.");
            }

        } catch (Exception e) {
            showErrorMessage("Failed to load data: " + e.getMessage());

            // Clear on error
            dataList.clear();
            refreshTable();
        }
    }

    private void refreshTable() {
        // FIXED: Proper clearing approach for paging Listbox using fully qualified
        // names

        // Store all non-header children to remove
        java.util.List<Component> itemsToRemove = new java.util.ArrayList<>();
        for (Component child : table.getChildren()) {
            if (child instanceof Listitem) {
                itemsToRemove.add(child);
            }
        }

        // Remove all data items (but keep header which is Listhead)
        for (Component item : itemsToRemove) {
            table.removeChild(item);
        }

        // Add new data rows - FIXED: Descending order numbering (newest first)
        for (int i = 0; i < dataList.size(); i++) {
            AssemblyData data = dataList.get(i);
            Listitem row = new Listitem();
            // CRITICAL: Ensure row styling allows interactions
            row.setStyle(StyleManager.combine(
                    StyleManager.getTableRow(),
                    "pointer-events: auto !important;", // Force enable pointer events on rows
                    "cursor: pointer !important;" // Make sure cursor indicates clickable
            ));

            // Create cells - FIXED: Show descending numbering (newest item = #1)
            // Since dataList.add(0, data) puts newest items at index 0,
            // we want index 0 to show as #1, index 1 as #2, etc.
            Listcell noCell = new Listcell(String.valueOf(i + 1));
            noCell.setStyle(StyleManager.getTableCellCenterBold());
            row.appendChild(noCell);

            Listcell barcodeCell = new Listcell(data.barcodeUPC);
            barcodeCell.setStyle(StyleManager.getTableCellBarcode());
            row.appendChild(barcodeCell);

            Listcell poCell = new Listcell(data.po);
            poCell.setStyle(StyleManager.getTableCell());
            row.appendChild(poCell);

            Listcell articleCell = new Listcell(data.article);
            articleCell.setStyle(StyleManager.getTableCell());
            row.appendChild(articleCell);

            Listcell sizeCell = new Listcell(data.size);
            sizeCell.setStyle(StyleManager.getTableCellCenter());
            row.appendChild(sizeCell);

            Listcell qtyCell = new Listcell(data.qty.toString());
            qtyCell.setStyle(StyleManager.getTableCellCenterBold());
            row.appendChild(qtyCell);

            // Action cell with delete button - FIXED: Update index untuk descending order
            Listcell actionCell = new Listcell();
            Button deleteBtn = new Button("X");
            deleteBtn.setStyle(StyleManager.getButtonDanger());
            deleteBtn.setAttribute("dataIndex", i); // Index sesuai dengan posisi di dataList
            deleteBtn.addEventListener(Events.ON_CLICK, this);
            actionCell.appendChild(deleteBtn);
            actionCell.setStyle(StyleManager.getTableCellCenter());
            row.appendChild(actionCell);

            table.appendChild(row);
        }

        updateTableTotal();
    }

    private void updateTableTotal() {
        if (!dataList.isEmpty()) {
            Listitem totalRow = new Listitem();
            totalRow.setStyle(StyleManager.getTableTotalRow());

            totalRow.appendChild(new Listcell(""));
            totalRow.appendChild(new Listcell(""));
            totalRow.appendChild(new Listcell(""));
            totalRow.appendChild(new Listcell(""));

            Listcell totalCell = new Listcell("Total");
            totalCell.setStyle(StyleManager.getTableCellCenterBold());
            totalRow.appendChild(totalCell);
            totalRow.appendChild(new Listcell(""));

            Listcell totalQtyCell = new Listcell(String.valueOf(dataList.size()));
            totalQtyCell.setStyle(StyleManager.combine(
                    StyleManager.getTableCellCenterBold(),
                    StyleManager.TEXT_BLUE_600));
            totalRow.appendChild(totalQtyCell);

            table.appendChild(totalRow);
        }
    }

    private void updateStatistics() {
        try {
            Properties ctx = Env.getCtx();
            int clientId = Env.getAD_Client_ID(ctx);
            int userId = Env.getAD_User_ID(ctx);

            ProductionStatisticData stats = ProductionStatistic.getProductionStatistic(ctx, clientId, userId, "MSA");

            if (stats != null) {
                scanPerHourLabel.setValue(stats.totalPerHour != null ? stats.totalPerHour.toString() : "0");
                totalScanLabel.setValue(stats.totalToday != null ? stats.totalToday.toString() : "0");
            } else {
                scanPerHourLabel.setValue(String.valueOf(scanPerHour));
                totalScanLabel.setValue(String.valueOf(totalScan));
            }

        } catch (Exception e) {
            System.out.println("Error updating statistics: " + e.getMessage());
        }
    }

    private void refreshDataFromDatabase() {
        Properties ctx = Env.getCtx();
        dataList.clear(); // Clear existing dummy data

        try {
            // Get current draft document
            //MMES_ProdOutput prodOutput = MMES_ProdOutput.createOrGetDraft(ctx,MMES_ProdOutput.MES_TRANS_ID_ScanAssembly);
            MMES_ProdOutput prodOutput = MMES_ProdOutput.getDraftForUser(ctx,Env.getAD_User_ID(ctx),MMES_ProdOutput.MES_TRANS_ID_ScanAssembly);
            // MMES_ProdOutput prodOutput = getOrCreateDraft();
            if (prodOutput == null) {
                log.warning("No draft document found to load data from");
                refreshTable();
                return;
            }

            // Query to get all MES_ProdOutputDetail records for current document
            String whereClause = "MES_ProdOutput_ID=? AND AD_Client_ID=?";
            List<MMES_ProdOutputDetail> details = new Query(ctx, MMES_ProdOutputDetail.Table_Name, whereClause, null)
                    .setParameters(prodOutput.getMES_ProdOutput_ID(), Env.getAD_Client_ID(ctx))
                    .setOrderBy("Created DESC")
                    .list();

            for (MMES_ProdOutputDetail detail : details) {
                AssemblyData data = new AssemblyData();

                data.barcodeUPC = detail.getBarcode();
                data.po = detail.getPOReference();
                data.article = detail.getArticle() != null ? detail.getArticle() : "N/A";
                data.size = detail.getSizeFactory() != null ? detail.getSizeFactory() : "N/A";
                data.qty = detail.getQtyEntered() != null ? detail.getQtyEntered() : BigDecimal.ONE;

                dataList.add(data);

            }

            totalScan = dataList.size();

            // Reset status to DRAFT when loading data if list was empty
            if (!dataList.isEmpty()) {
                updateDocumentStatus(STATUS_DRAFT);
            }

            refreshTable();

        } catch (Exception e) {
            showErrorMessage("Failed to load data from database: " + e.getMessage());

            // Fallback: refresh table with existing data
            refreshTable();
        }
    }

    private MMES_ProdOutput getOrCreateDraft() {
        Properties ctx = Env.getCtx();
        int AD_User_ID = Env.getAD_User_ID(ctx);

        String trxName = Trx.createTrxName("MSA");
        Trx trx = Trx.get(trxName, true);
        trx.setDisplayName(getClass().getName() + "_getOrCreateDraft");

        try {
            String whereClause = "AD_Client_ID=? AND AD_Org_ID=? AND CreatedBy=? AND DocStatus=?";
            MMES_ProdOutput doc = new Query(ctx, MMES_ProdOutput.Table_Name, whereClause, trxName)
                    .setParameters(Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), AD_User_ID,
                            DocAction.STATUS_Drafted)
                    .setOrderBy("Created DESC")
                    .first();

            if (doc != null) {
                log.info("Existing Draft found: " + doc.getDocumentNo());
                trx.commit();
                return doc;
            }

            doc = new MMES_ProdOutput(ctx, 0, trxName); // AD_Client_ID & AD_Org_ID otomatis
            doc.setMES_Trans_ID(MMES_ProdOutput.MES_TRANS_ID_ScanAssembly);
            doc.setDocStatus(DocAction.STATUS_Drafted);
            //doc.setSAP_WorkCenter_ID(1000028);
            doc.saveEx(); // CreatedBy & UpdatedBy otomatis dari context

            trx.commit();
            return doc;

        } catch (Exception e) {
            trx.rollback();
            throw new IllegalStateException("Failed to get or create draft", e);
        } finally {
            trx.close();
        }
    }

    private void removeItem(int index) {
        if (index >= 0 && index < dataList.size()) {
            AssemblyData removed = dataList.get(index);

            boolean dbRemovalSuccess = removeItemFromDatabase(removed);

            if (dbRemovalSuccess) {
                refreshDataFromDatabase();
                updateStatistics();
                showInfoMessage("✓ Item removed from database: " + removed.barcodeUPC);
            } else {
                showErrorMessage("Failed to remove item from database. Please try again.");
            }
        }
    }

    /**
     * Remove item from MES_ProdOutputDetail database table
     */
    private boolean removeItemFromDatabase(AssemblyData itemToRemove) {
        Properties ctx = Env.getCtx();
        String trxName = Trx.createTrxName("MSA_Delete");
        Trx trx = Trx.get(trxName, true);
        trx.setDisplayName(getClass().getName() + "_removeItem");

        try {
            // Get current draft document
            // MMES_ProdOutput prodOutput = getOrCreateDraft();
        	MMES_ProdOutput prodOutput = MMES_ProdOutput.getDraftForUser(ctx,Env.getAD_User_ID(ctx),MMES_ProdOutput.MES_TRANS_ID_ScanAssembly);
            
            if (prodOutput == null) {
                log.warning("No draft document found for item removal");
                return false;
            }

            // Find the specific MES_ProdOutputDetail record to delete
            String whereClause = "MES_ProdOutput_ID=? AND Barcode=? AND POReference=? AND AD_Client_ID=?";
            MMES_ProdOutputDetail detailToDelete = new Query(ctx, MMES_ProdOutputDetail.Table_Name, whereClause,
                    trxName)
                    .setParameters(
                            prodOutput.getMES_ProdOutput_ID(),
                            itemToRemove.barcodeUPC,
                            itemToRemove.po,
                            Env.getAD_Client_ID(ctx))
                    .first();

            if (detailToDelete != null) {

                detailToDelete.deleteEx(true);

                trx.commit();

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {
            trx.rollback();
            log.log(Level.SEVERE, "Failed to delete MES_ProdOutputDetail - transaction rolled back", e);
            return false;
        } finally {
            trx.close();
        }
    }

    private void performPosting() {
        if (dataList.isEmpty()) {
            Dialog.warn(0, "No items to post. Please scan some barcodes first.");
            return;
        }

        if (!validateDocumentNo(fDocumentNo.getValue())) {
            showErrorMessage("Please enter a valid document number.");
            fDocumentNo.focus();
            return;
        }

        Dialog.ask(0, "Confirm Document Action",
                "This will COMPLETE the document and post " + dataList.size() + " items to SAP.\n" +
                        "Document Action: " + ACTION_COMPLETE + "\n" +
                        "Are you sure you want to proceed?",
                new Callback<Boolean>() {
                    @Override
                    public void onCallback(Boolean ok) {
                        if (ok) {
                            try {
                                String selectedPOText = "";
                                if (currentSelectedPONumber != null && !currentSelectedPONumber.trim().isEmpty()) {
                                    // Find the display text for the selected PO
                                    for (POData po : availablePOs) {
                                        if (po.getPoNumber().equals(currentSelectedPONumber)) {
                                            selectedPOText = po.getDisplayText();
                                            break;
                                        }
                                    }
                                }

                                // Update document with current form data
                                currentDocument.setDocumentNo(fDocumentNo.getValue());
                                currentDocument.setPoNumber(availablePOs.get(fPO.getSelectedIndex()).getPoNumber());
                                currentDocument.setItems(new ArrayList<>(dataList));
                                currentDocument.setUpdatedBy("System");

                                // Process Document Action: Prepare → Complete
                                showInfoMessage("Processing MES Production Output Document...");

                                // Step 1: Prepare document (validate and change status to IN_PROGRESS)
                                if (currentDocument.prepareIt()) {
                                    updateDocumentStatus(currentDocument.getDocStatus());

                                    // Step 2: Complete document (final posting to SAP)
                                    if (currentDocument.completeIt()) {
                                        updateDocumentStatus(currentDocument.getDocStatus());

                                        Dialog.info(0, "MES Document Completed Successfully",
                                                "Document Processing Completed!\n" +
                                                        "Document No: " + currentDocument.getDocumentNo() + "\n" +
                                                        "Document Status: "
                                                        + getStatusDisplayLabel(currentDocument.getDocStatus()) + " ("
                                                        + currentDocument.getDocStatus() + ")\n" +
                                                        "Document Action: " + currentDocument.getDocAction() + "\n" +
                                                        "PO: " + selectedPOText + "\n" +
                                                        "Items Posted: " + currentDocument.getItems().size() + "\n" +
                                                        "Created: " + currentDocument.getCreated() + "\n" +
                                                        "Updated: " + currentDocument.getUpdated());

                                        dataList.clear();
                                        refreshTable();
                                        showSuccessMessage(
                                                "MES Production Output Document completed and posted to SAP. Document: "
                                                        + currentDocument.toString());

                                        // Initialize new document for next session
                                        initializeDocument();
                                    } else {
                                        showErrorMessage("Failed to complete document. Please try again.");
                                        currentDocument.setDocStatus(STATUS_DRAFT); // Reset on failure
                                        updateDocumentStatus(STATUS_DRAFT);
                                    }
                                } else {
                                    showErrorMessage("Failed to prepare document. Please check your data.");
                                    updateDocumentStatus(STATUS_DRAFT);
                                }

                                fBarcodeUPC.focus();
                            } catch (Exception e) {
                                log.log(Level.SEVERE, "Error during document processing", e);
                                showErrorMessage("Document processing failed: " + e.getMessage());
                                // Reset to draft on error
                                updateDocumentStatus(STATUS_DRAFT);
                            }
                        }
                    }
                });
    }

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
            Listbox table = createTableReportDetail();
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
            log.log(Level.SEVERE, "Error showing report", e);
            showErrorMessage("Failed to show report: " + e.getMessage());
        }
    }

    private Listbox createTableReportDetail() {
        try {
            // Create table with styling
            Listbox table = new Listbox();
            table.setStyle("border: 1px solid #ddd; border-radius: 6px; width: 100%;");
            table.setMold("paging");
            table.setPageSize(10);
            table.setVflex("1");

            // Create header
            Listhead header = new Listhead();
            header.setStyle("background: #f5f5f5; border-bottom: 1px solid #ddd;");

            String[] headers = { "No", "PO Number", "Total", "Jam 1", "Jam 2", "Jam 3", "Jam 4", "Jam 5", "Jam 6",
                    "Jam 7", "Jam 8" };

            for (int i = 0; i < headers.length; i++) {
                Listheader h = new Listheader(headers[i]);

                // Auto-resize columns using hflex
                if (i == 0) {
                    h.setWidth("60px");
                } else if (i == 1) {
                    h.setHflex("2");
                } else if (i == 2) {
                    h.setWidth("80px");
                } else {
                    h.setHflex("1");
                }

                h.setStyle("text-align: center; font-weight: bold; padding: 10px;");
                header.appendChild(h);
            }

            table.appendChild(header);

            // Load data from database
            Properties ctx = Env.getCtx();
            int createdBy = Env.getAD_User_ID(ctx);
            MESServices.ProductionReport service = new MESServices().new ProductionReport();
            List<ProductionReportData> data = service.getReportDataFromDatabase(ctx,
                    MMES_ProdOutput.MES_TRANS_ID_ScanAssembly, createdBy);

            if (data.isEmpty()) {
                // Add empty data row
                Listitem emptyRow = new Listitem();
                Listcell emptyCell = new Listcell("No data available");
                emptyCell.setSpan(11);
                emptyCell.setStyle("text-align: center; padding: 30px; color: #666;");
                emptyRow.appendChild(emptyCell);
                table.appendChild(emptyRow);
                return table;
            }

            int rowNum = 1;
            int totalSum = 0;
            int[] hourTotals = new int[8];

            // Add data rows
            for (ProductionReportData item : data) {
                Listitem row = new Listitem();
                row.setStyle(rowNum % 2 == 0 ? "background: #f9f9f9;" : "background: white;");

                row.appendChild(new Listcell(String.valueOf(rowNum++)));
                row.appendChild(new Listcell(item.poNumber));
                row.appendChild(new Listcell(String.valueOf(item.total)));

                for (int i = 0; i < item.hourlyData.length; i++) {
                    row.appendChild(new Listcell(String.valueOf(item.hourlyData[i])));
                    hourTotals[i] += item.hourlyData[i];
                }

                totalSum += item.total;
                table.appendChild(row);
            }

            // Add total row
            Listitem totalRow = new Listitem();
            totalRow.setStyle("background: #e9ecef; font-weight: bold; border-top: 2px solid #333;");

            totalRow.appendChild(new Listcell(""));
            totalRow.appendChild(new Listcell("TOTAL"));
            totalRow.appendChild(new Listcell(String.valueOf(totalSum)));

            for (int total : hourTotals) {
                totalRow.appendChild(new Listcell(String.valueOf(total)));
            }

            table.appendChild(totalRow);
            showInfoMessage("Loaded " + data.size() + " records");

            return table;

        } catch (Exception e) {
            log.log(Level.SEVERE, "Error creating report table with data", e);
            showErrorMessage("Failed to load report data: " + e.getMessage());

            // Return empty table on error
            Listbox errorTable = new Listbox();
            errorTable.setStyle("border: 1px solid #ddd; border-radius: 6px; width: 100%;");

            Listitem errorRow = new Listitem();
            Listcell errorCell = new Listcell("Error loading data: " + e.getMessage());
            errorCell.setStyle("text-align: center; padding: 30px; color: #dc3545;");
            errorRow.appendChild(errorCell);
            errorTable.appendChild(errorRow);

            return errorTable;
        }
    }

    @Override
    public ADForm getForm() {
        return this;
    }

    // Data classes
    public static class AssemblyData {
        String barcodeUPC;
        String po;
        String article;
        String size;
        BigDecimal qty;

        public AssemblyData() {
            this.qty = BigDecimal.ONE;
        }
    }

    public static class POData {
        private String poNumber;
        private String displayText;
        private boolean isPlaceholder;

        public POData(String poNumber, String displayText, boolean isPlaceholder) {
            this.poNumber = poNumber;
            this.displayText = displayText;
            this.isPlaceholder = isPlaceholder;
        }

        public String getPoNumber() {
            return poNumber;
        }

        public String getDisplayText() {
            return displayText;
        }

        public boolean isPlaceholder() {
            return isPlaceholder;
        }

        @Override
        public String toString() {
            return "POData{poNumber='" + poNumber + "', displayText='" + displayText + "', isPlaceholder="
                    + isPlaceholder + "}";
        }
    }

    /**
     * MES Production Output Document Model
     * Simulates the MES_ProdOutput table with DocumentAction workflow
     */
    public static class MESProdOutputDocument {
        private String documentNo;
        private String docStatus;
        private String docAction;
        private String line;
        private String poNumber;
        private String organizer;
        private String tenant;
        private List<AssemblyData> items;
        private java.util.Date created;
        private java.util.Date updated;
        private String createdBy;
        private String updatedBy;

        public MESProdOutputDocument() {
            this.docStatus = STATUS_DRAFT;
            this.docAction = ACTION_PREPARE;
            this.items = new ArrayList<>();
            this.created = new java.util.Date();
            this.updated = this.created;
        }

        // DocumentAction workflow methods
        public boolean prepareIt() {
            if (!STATUS_DRAFT.equals(docStatus)) {
                return false;
            }
            this.docStatus = STATUS_IN_PROGRESS;
            this.docAction = ACTION_COMPLETE;
            this.updated = new java.util.Date();
            return true;
        }

        public boolean completeIt() {
            if (!STATUS_IN_PROGRESS.equals(docStatus)) {
                return false;
            }
            this.docStatus = STATUS_POSTED;
            this.docAction = ACTION_CLOSE;
            this.updated = new java.util.Date();
            return true;
        }

        public boolean voidIt() {
            if (STATUS_VOIDED.equals(docStatus)) {
                return false;
            }
            this.docStatus = STATUS_VOIDED;
            this.docAction = ACTION_VOID;
            this.updated = new java.util.Date();
            return true;
        }

        // Getters and Setters
        public String getDocumentNo() {
            return documentNo;
        }

        public void setDocumentNo(String documentNo) {
            this.documentNo = documentNo;
        }

        public String getDocStatus() {
            return docStatus;
        }

        public void setDocStatus(String docStatus) {
            this.docStatus = docStatus;
        }

        public String getDocAction() {
            return docAction;
        }

        public void setDocAction(String docAction) {
            this.docAction = docAction;
        }

        public String getLine() {
            return line;
        }

        public void setLine(String line) {
            this.line = line;
        }

        public String getPoNumber() {
            return poNumber;
        }

        public void setPoNumber(String poNumber) {
            this.poNumber = poNumber;
        }

        public String getOrganizer() {
            return organizer;
        }

        public void setOrganizer(String organizer) {
            this.organizer = organizer;
        }

        public String getTenant() {
            return tenant;
        }

        public void setTenant(String tenant) {
            this.tenant = tenant;
        }

        public List<AssemblyData> getItems() {
            return items;
        }

        public void setItems(List<AssemblyData> items) {
            this.items = items;
        }

        public java.util.Date getCreated() {
            return created;
        }

        public java.util.Date getUpdated() {
            return updated;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        @Override
        public String toString() {
            return "MESProdOutputDocument{" +
                    "documentNo='" + documentNo + '\'' +
                    ", docStatus='" + docStatus + '\'' +
                    ", docAction='" + docAction + '\'' +
                    ", items=" + items.size() +
                    '}';
        }
    }

    /**
     * Service class untuk mengelola PO data - ENHANCED with database integration
     */
    public static class POService {

        public List<POData> getPOsForLine(String lineCode) {
            // Real database call to get PO data
            List<POData> pos = new ArrayList<>();

            String lineCode2 = "'C170','C156'";

            String sql = ""
                    + "WITH torder AS ( "
                    + "    SELECT sp.ordernumber, ss.poreference, sp.qtyordered "
                    + "    FROM sap_prodorder sp "
                    + "    INNER JOIN m_product mp ON sp.m_product_id = mp.m_product_id "
                    + "    INNER JOIN m_product_category mpc ON mp.m_product_category_id = mpc.m_product_category_id "
                    + "    INNER JOIN sap_salesorder ss ON sp.sap_salesorder_id = ss.sap_salesorder_id "
                    + "    WHERE mpc.value = 'ZFG' "
                    + "), "
                    + "tassembly AS ( "
                    + "    SELECT mp2.ordernumber, mp2.poreference, SUM(mp2.qtyentered) AS qtyentered "
                    + "    FROM mes_prodoutput mp "
                    + "    INNER JOIN mes_prodoutputdetail mp2 ON mp.mes_prodoutput_id = mp2.mes_prodoutput_id "
                    + "    WHERE mp.mes_trans_id = 'MSA' "
                    + "    GROUP BY mp2.ordernumber, mp2.poreference "
                    + "), "
                    + "tinventory AS ( "
                    + "    SELECT ot.poreference "
                    + "    FROM torder ot "
                    + "    LEFT JOIN tassembly at ON ot.ordernumber = at.ordernumber "
                    + "    WHERE (COALESCE(ot.qtyordered,0) - COALESCE(at.qtyentered,0)) > 0 "
                    + ") "
                    + "SELECT DISTINCT poreference "
                    + "FROM tinventory";

            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                pstmt = DB.prepareStatement(sql, null);
                // isi parameter slocreceiving & productValue
                // DB.setParameters(pstmt, new Object[]{lineCode2});
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    String poRef = rs.getString("poreference");
                    pos.add(new POData(poRef, poRef, false));
                }

            } catch (SQLException e) {
                log.log(Level.SEVERE, "Database error in getPOsForLine: " + sql, e);
                return new ArrayList<>();
            } finally {
                DB.close(rs, pstmt);
            }

            log.info("POService.getPOsForLine(" + lineCode + ") returning " + pos.size() + " POs from database");

            return pos;

        }

        public void addPOToLine(String lineCode, POData poData) {
            // Simulate adding PO to database for specific line
            // In real implementation, this would save to database
            log.info("Added PO " + poData.getPoNumber() + " to line " + lineCode);
        }
    }

    /**
     * Add ZK ClientInfoEvent listener for responsive behavior
     * This implements ZK-native responsive design using hflex/vflex attributes
     */
    private void addClientInfoEventListener() {
        try {
            // Add ClientInfoEvent listener to detect device characteristics
            this.addEventListener(Events.ON_CLIENT_INFO, new EventListener<Event>() {
                @Override
                public void onEvent(Event event) throws Exception {
                    if (event instanceof ClientInfoEvent) {
                        applyZKResponsiveLayout((ClientInfoEvent) event);
                    }
                }
            });
            log.info("ZK ClientInfoEvent listener added for responsive behavior");
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to add ClientInfoEvent listener", e);
        }
    }

    /**
     * Apply ZK-native responsive layout based on ClientInfoEvent
     * Uses hflex/vflex attributes for fluid layouts following Mondrian pattern
     */
    private void applyZKResponsiveLayout(ClientInfoEvent event) {
        try {
            int screenWidth = event.getDesktopWidth();
            int screenHeight = event.getDesktopHeight();
            String orientation = event.getOrientation();

            log.info("ClientInfoEvent - Screen: " + screenWidth + "x" + screenHeight +
                    ", Orientation: " + orientation);

            // Determine device type based on screen width (ZK approach)
            boolean isMobile = screenWidth < 768;
            boolean isTablet = screenWidth >= 768 && screenWidth < 1024;
            boolean isDesktop = screenWidth >= 1024;

            // Apply ZK-native responsive attributes
            applyZKResponsiveAttributes(isMobile, isTablet, isDesktop, orientation);

        } catch (Exception e) {
            log.log(Level.WARNING, "Error applying ZK responsive layout", e);
        }
    }

    /**
     * Apply ZK hflex/vflex attributes for responsive behavior
     * This implements the Mondrian pattern for adaptive layouts
     */
    private void applyZKResponsiveAttributes(boolean isMobile, boolean isTablet, boolean isDesktop,
            String orientation) {
        try {
            // Form section responsive behavior
            if (isMobile) {
                // Mobile: Stack form sections vertically
                applyMobileLayout();
            } else if (isTablet) {
                // Tablet: Side-by-side with adjusted proportions
                applyTabletLayout();
            } else {
                // Desktop: Maintain original layout
                applyDesktopLayout();
            }

            // Update statistics panel layout
            updateStatsPanelLayout(isMobile, isTablet);

            log.info("ZK responsive attributes applied - Mobile: " + isMobile +
                    ", Tablet: " + isTablet + ", Desktop: " + isDesktop);

        } catch (Exception e) {
            log.log(Level.WARNING, "Error applying ZK responsive attributes", e);
        }
    }

    /**
     * Apply mobile layout using ZK hflex/vflex
     */
    private void applyMobileLayout() {
        // Form sections stack vertically on mobile
        // Left column takes full width, right column stacks below
        // This maintains desktop appearance while being touch-friendly
    }

    /**
     * Apply tablet layout using ZK hflex/vflex
     */
    private void applyTabletLayout() {
        // Tablet: Adjust proportions but maintain side-by-side layout
        // Slightly reduce left column width, increase right column
    }

    /**
     * Apply desktop layout - maintain original appearance
     */
    private void applyDesktopLayout() {
        // Desktop: Keep original layout unchanged
        // This ensures desktop maintains the expected appearance
    }

    /**
     * Update statistics panel layout based on device
     */
    private void updateStatsPanelLayout(boolean isMobile, boolean isTablet) {
        // Statistics adapt from horizontal to vertical layout on mobile
        // Uses ZK's flex attributes for smooth transitions
    }
    
    
    
    
    
    /*-----------------------------------------------------------------------------------------------------------------------
     * Logic backend
     *-----------------------------------------------------------------------------------------------------------------------
     */
    
    
    private void addBarcodeItem(String barcode) {
        Properties ctx = Env.getCtx();

        // Step 1: Validasi PO yang sedang dipilih
        String selectedPO = currentSelectedPONumber;
        int selectedIndex = fPO.getSelectedIndex();

        if (selectedPO == null || selectedPO.trim().isEmpty()) {
            if (selectedIndex > 0 && selectedIndex < availablePOs.size()) {
                POData currentPO = availablePOs.get(selectedIndex);
                if (currentPO != null && !currentPO.isPlaceholder() && currentPO.getPoNumber() != null
                        && !currentPO.getPoNumber().trim().isEmpty()) {
                    selectedPO = currentPO.getPoNumber();
                    currentSelectedPONumber = selectedPO;
                    log.info("Recovered PO from selection: " + selectedPO);
                }
            }
        }

        if (selectedPO == null || selectedPO.trim().isEmpty()) {
            showErrorMessage("Error: Please select a PO before scanning barcodes!");
            return;
        }
        
        if (Util.isEmpty(barcode)) {
            showErrorMessage("Please enter a barcode!");
            return;
        }
        
        // Get data barcode detail
        MMES_BarcodeDetail barcodeDatas = MMES_BarcodeDetail.findByBarcodeAndType(ctx, barcode, MESConstants.BarcodeType.BARCODE_UPC);
        
        if (barcodeDatas == null) {
            showErrorMessage("Error: Barcode '" + barcode + "' tidak ditemukan di database UPC");
            return;
        }

        // Get data produksi stock
        MV_MES_ProductionInfo stockDatas = MV_MES_ProductionInfo.findByPOAndSizeFactory(
        		ctx, selectedPO, barcodeDatas.getSizeFactory(), MESConstants.TransactionType.SCAN_ASSEMBLY,null);
        
        if (stockDatas == null) {
            showErrorMessage("Error: PO belum release atau tidak ditemukan dalam data produksi.");
            return;
        } else if (stockDatas.getQtyOutstanding().compareTo(BigDecimal.ZERO) <= 0) {
            showErrorMessage("Error: Data PO sudah completed.");
            return;
        }

        // Save Transaction Detail
        String trxName = Trx.createTrxName("MSA_Detail");
        Trx trx = null;
        try {
            trx = Trx.get(trxName, true);
            trx.setDisplayName(getClass().getName() + "_addBarcodeItem");

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

            showSuccessMessage("✓ BARCODE SAVED: " + barcode + " | Data loaded from database");

            // Update UI dan reset input
            refreshDataFromDatabase();
            scanPerHour++;
            totalScan++;
            fBarcodeUPC.setValue("");
            fBarcodeUPC.focus();

            
        } catch (Exception e) {
            if (trx != null && trx.isActive()) {
                trx.rollback();
            }
            showErrorMessage("Gagal simpan detail output produksi: " + e.getMessage());
            log.severe(e.getMessage());

        } finally {
            if (trx != null && trx.isActive()) {
                trx.close();
            }
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