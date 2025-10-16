package id.co.bintangindokarya.mes.form;

/**
 * Style Manager for ZK Framework Components
 * Provides TailwindCSS-like utility classes for consistent styling
 * 
 * @author BIG Development Team
 */
public class StyleManager {

    // ========== SIZE CONSTANTS ==========
    public static final String SIZE_60PX = "60px";
    public static final String SIZE_80PX = "80px";
    public static final String SIZE_100PX = "100px";
    public static final String SIZE_120PX = "120px";
    public static final String SIZE_160PX = "160px";
    public static final String SIZE_200PX = "200px";
    public static final String SIZE_320PX = "320px";

    // ========== LAYOUT UTILITIES ==========
    public static final String FLEX = "display: flex;";
    public static final String FLEX_COL = "display: flex; flex-direction: column;";
    public static final String FLEX_ROW = "display: flex; flex-direction: row;";
    public static final String JUSTIFY_CENTER = "justify-content: center;";
    public static final String JUSTIFY_BETWEEN = "justify-content: space-between;";
    public static final String ITEMS_CENTER = "align-items: center;";
    public static final String GRID = "display: grid;";
    public static final String GRID_COLS_2 = "grid-template-columns: 1fr 1fr;";

    // ========== SPACING UTILITIES ==========
    public static final String GAP_2 = "gap: 8px;";
    public static final String GAP_3 = "gap: 12px;";
    public static final String GAP_4 = "gap: 16px;";
    public static final String P_2 = "padding: 8px;";
    public static final String P_3 = "padding: 12px;";
    public static final String P_4 = "padding: 16px;";
    public static final String P_5 = "padding: 20px;";
    public static final String P_12 = "padding: 12px;";
    public static final String P_30 = "padding: 30px;";
    public static final String PX_3 = "padding-left: 12px; padding-right: 12px;";
    public static final String PY_2 = "padding-top: 8px; padding-bottom: 8px;";
    public static final String M_2 = "margin: 8px;";
    public static final String MB_2 = "margin-bottom: 8px;";
    public static final String MB_3 = "margin-bottom: 12px;";
    public static final String MB_4 = "margin-bottom: 16px;";
    public static final String MB_10 = "margin-bottom: 10px;";
    public static final String MB_15 = "margin-bottom: 15px;";
    public static final String ML_2 = "margin-left: 8px;";
    public static final String MT_2 = "margin-top: 8px;";
    public static final String MT_3 = "margin-top: 12px;";
    public static final String MT_25 = "margin-top: 25px;";
    public static final String MR_2 = "margin-right: 8px;";

    // ========== SIZING UTILITIES ==========
    public static final String W_FULL = "width: 100%;";
    public static final String W_250 = "width: 250px;";
    public static final String W_100 = "width: 100px;";
    public static final String H_FULL = "height: 100%;";
    public static final String H_60 = "height: 60px;";
    public static final String H_40 = "min-height: 40px;";
    public static final String MIN_W_0 = "min-width: 0;";
    public static final String MIN_W_280 = "min-width: 280px;";
    public static final String MIN_W_580 = "min-width: 580px;";
    public static final String MIN_H_40 = "min-height: 170px;";
    public static final String MIN_H_80 = "min-height: 320px;";
    public static final String MAX_W_SM = "max-width: 140px;"; // Compact to eliminate overflow
    public static final String MAX_W_MD = "max-width: 580px;";
    public static final String MAX_W_LG = "max-width: 650px;";
    public static final String MAX_W_XL = "max-width: 300px;";

    // ========== TYPOGRAPHY UTILITIES ==========
    public static final String TEXT_XS = "font-size: 12px;";
    public static final String TEXT_SM = "font-size: 13px;";
    public static final String TEXT_BASE = "font-size: 14px;";
    public static final String TEXT_LG = "font-size: 16px;";
    public static final String TEXT_XL = "font-size: 18px;";
    public static final String TEXT_2XL = "font-size: 20px;";
    public static final String TEXT_3XL = "font-size: 24px;";
    public static final String TEXT_4XL = "font-size: 42px;";
    public static final String FONT_BOLD = "font-weight: bold;";
    public static final String TEXT_CENTER = "text-align: center;";
    public static final String TEXT_RIGHT = "text-align: right;";
    public static final String LINE_HEIGHT_1 = "line-height: 1;";
    public static final String LINE_HEIGHT_RELAXED = "line-height: 1.5;";

    // ========== COLOR UTILITIES ==========
    public static final String TEXT_GRAY_600 = "color: #6c757d;";
    public static final String TEXT_GRAY_800 = "color: #333;";
    public static final String TEXT_GRAY_500 = "color: #666;";
    public static final String TEXT_BLUE_600 = "color: #007bff;";
    public static final String TEXT_GREEN_600 = "color: #28a745;";
    public static final String TEXT_RED_600 = "color: #dc3545;";
    public static final String TEXT_WHITE = "color: white;";

    public static final String BG_WHITE = "background-color: white;";
    public static final String BG_GRAY_50 = "background-color: #ffffff;";
    public static final String BG_GRAY_100 = "background-color: #f5f5f5;";
    public static final String BG_BLUE_50 = "background-color: #e3f2fd;";
    public static final String BG_YELLOW_50 = "background-color: #ffffcc;";
    public static final String BG_BLUE_600 = "background-color: #007bff;";
    public static final String BG_GREEN_600 = "background-color: #28a745;";
    public static final String BG_RED_600 = "background-color: #dc3545;";
    public static final String BG_GRAY_600 = "background-color: #6c757d;";
    public static final String BG_YELLOW_400 = "background-color: #ffc107;";

    // ========== BORDER UTILITIES ==========
    public static final String BORDER = "border: 1px solid #ced4da;";
    public static final String BORDER_GRAY_300 = "border: 1px solid #ddd;";
    public static final String BORDER_BLUE_300 = "border: 1px solid #90caf9;";
    public static final String BORDER_NONE = "border: none;";
    public static final String BORDER_TOP_2 = "border-top: 2px solid #dee2e6;";
    public static final String BORDER_BOTTOM_1 = "border-bottom: 1px solid #ddd;";
    public static final String BORDER_BOTTOM_2 = "border-bottom: 2px solid #ddd;";
    public static final String ROUNDED = "border-radius: 4px;";
    public static final String ROUNDED_MD = "border-radius: 6px;";
    public static final String ROUNDED_LG = "border-radius: 10px;";
    public static final String ROUNDED_SM = "border-radius: 3px;";

    // ========== SHADOW UTILITIES ==========
    public static final String SHADOW_SM = "box-shadow: 0 2px 4px rgba(0,0,0,0.1);";
    public static final String SHADOW_MD = "box-shadow: 0 3px 6px rgba(0,0,0,0.15);";
    public static final String SHADOW_LG = "box-shadow: 0 2px 4px rgba(0,0,0,0.2);";
    public static final String TEXT_SHADOW = "text-shadow: 1px 1px 2px rgba(0,0,0,0.1);";

    // ========== INTERACTIVE UTILITIES ==========
    public static final String CURSOR_POINTER = "cursor: pointer;";
    public static final String BOX_SIZING_BORDER = "box-sizing: border-box;";
    public static final String OVERFLOW_HIDDEN = "overflow: hidden;";
    public static final String OVERFLOW_AUTO = "overflow: auto;";
    public static final String DISPLAY_BLOCK = "display: block;";
    public static final String FLEX_SHRINK_0 = "flex-shrink: 0;";
    public static final String FLEX_1 = "flex: 1;";
    public static final String FLEX_0_0_AUTO = "flex: 0 0 auto;";

    // ========== ENHANCED INPUT & SELECT STYLES ==========

    // Style untuk input yang bisa diedit dengan default value
    public static final String INPUT_EDITABLE = "width: 100%; height: 36px; padding: 8px 12px; font-size: 13px; border: 1px solid #d1d5db; border-radius: 6px; background-color: white; box-sizing: border-box; transition: border-color 0.2s, box-shadow 0.2s;";
    public static final String INPUT_EDITABLE_FOCUSED = "border-color: #3b82f6; box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.1);";

    // Style untuk input dengan default value styling
    public static final String INPUT_DEFAULT_VALUE = "color: #6b7280; font-style: italic;";
    public static final String INPUT_USER_VALUE = "color: #1f2937; font-style: normal;";


    // Responsive
    public static final String BREAKPOINT_TABLET = "@media (max-width: 768px)";
    public static final String BREAKPOINT_LAPTOP = "@media (max-width: 1024px)";
    public static final String BREAKPOINT_MOBILE = "@media (max-width: 600px)";
    public static final String BREAKPOINT_DESKTOP = "@media (min-width: 1025px)";

    public static String getPaddingStandard() {
        return "padding: 12px 16px;";
    }

    // Method helper untuk gap umum
    public static String getGapStandard() {
        return "gap: 12px;";
    }

    // ========== UTILITY METHODS ==========

    /**
     * Combines multiple style strings into one
     * 
     * @param styles Variable number of style strings
     * @return Combined style string
     */
    public static String combine(String... styles) {
        StringBuilder combined = new StringBuilder();
        for (String style : styles) {
            if (style != null && !style.trim().isEmpty()) {
                combined.append(style);
                if (!style.endsWith(";")) {
                    combined.append(" ");
                }
            }
        }
        return combined.toString().trim();
    }

    // ========== ZK-NATIVE RESPONSIVE UTILITY METHODS ==========

    /**
     * ZK-native responsive container that adapts using hflex/vflex
     * Uses Mondrian pattern for fluid layouts
     */
    public static String getZKResponsiveContainer() {
        return combine(
            "width: 100%;",
            "box-sizing: border-box;",
            // ZK will handle responsive behavior via hflex/vflex attributes
            // This provides base styling for ZK components
            "min-width: 320px;" // Minimum width for mobile
        );
    }

    /**
     * ZK-native responsive form grid that changes from 2 columns to 1 column
     * Uses ClientInfoEvent detection for device characteristics
     */
    public static String getZKResponsiveFormGrid() {
        return combine(
            GRID,
            "grid-template-columns: repeat(2, 1fr);",
            getGapStandard(),
            W_FULL,
            BOX_SIZING_BORDER,
            // ZK components will use hflex/vflex for responsive behavior
            // CSS media queries used as fallback only
            BREAKPOINT_TABLET + " { grid-template-columns: 1fr; }"
        );
    }

    /**
     * ZK-native responsive flex container that stacks on mobile
     * Designed to work with ZK's vflex attribute
     */
    public static String getZKResponsiveFlexRowToCol() {
        return combine(
            FLEX_ROW,
            getGapStandard(),
            W_FULL,
            BOX_SIZING_BORDER,
            // ZK will handle responsive stacking via vflex attributes
            // CSS used as progressive enhancement
            BREAKPOINT_TABLET + " { flex-direction: column; }"
        );
    }

    /**
     * ZK-native responsive form field that adapts height and font size
     * Compatible with ZK's ClientInfoEvent for device detection
     */
    public static String getZKResponsiveFormField() {
        return combine(
            "height: 40px;",
            "font-size: 14px;",
            "padding: 10px 14px;",
            BOX_SIZING_BORDER,
            "transition: all 0.2s ease-in-out;",
            // ZK will adjust via ClientInfoEvent, CSS as fallback
            BREAKPOINT_TABLET + " { height: 44px; font-size: 16px; padding: 12px 16px; }",
            BREAKPOINT_MOBILE + " { height: 48px; font-size: 16px; padding: 14px 18px; }"
        );
    }

    /**
     * ZK-native responsive table container with adaptive height
     * Works with ZK's sizing attributes
     */
    public static String getZKResponsiveTableContainer() {
        return combine(
            "max-height: 400px;",
            "overflow-y: auto;",
            "overflow-x: hidden;",
            BOX_SIZING_BORDER,
            // ZK components will use vflex for height management
            BREAKPOINT_LAPTOP + " { max-height: 350px; overflow-y: auto; overflow-x: hidden; }",
            BREAKPOINT_TABLET + " { max-height: 300px; overflow-y: auto; overflow-x: hidden; }",
            BREAKPOINT_MOBILE + " { max-height: 250px; overflow-y: auto; overflow-x: hidden; }"
        );
    }

    /**
     * ZK-native responsive button that adapts size
     * Touch-friendly for mobile devices detected by ClientInfoEvent
     */
    public static String getZKResponsiveButton() {
        return combine(
            "padding: 10px 20px;",
            "font-size: 14px;",
            "min-height: 40px;",
            BOX_SIZING_BORDER,
            // ZK will optimize for touch targets based on device
            BREAKPOINT_TABLET + " { padding: 12px 24px; font-size: 16px; min-height: 44px; }",
            BREAKPOINT_MOBILE + " { padding: 14px 28px; font-size: 16px; min-height: 48px; }"
        );
    }

    /**
     * ZK-native responsive spacing utility
     * Adapts based on ZK's fluid layout patterns
     */
    public static String getZKResponsiveSpacing(String desktop, String tablet, String mobile) {
        return combine(
            "gap: " + desktop + ";",
            // ZK's Mondrian pattern handles spacing adaptation
            BREAKPOINT_LAPTOP + " { gap: " + tablet + "; }",
            BREAKPOINT_TABLET + " { gap: " + mobile + "; }"
        );
    }

    /**
     * ZK-native responsive text size
     * Compatible with ZK's ClientInfoEvent for optimal readability
     */
    public static String getZKResponsiveTextSize(String desktop, String tablet, String mobile) {
        return combine(
            "font-size: " + desktop + ";",
            // ZK adjusts text size based on device characteristics
            BREAKPOINT_LAPTOP + " { font-size: " + tablet + "; }",
            BREAKPOINT_TABLET + " { font-size: " + mobile + "; }"
        );
    }

    /**
     * ZK-native responsive layout for Borderlayout regions
     * Uses hflex/vflex for fluid North/Center/South adaptation
     */
    public static String getZKResponsiveBorderlayout() {
        return combine(
            W_FULL,
            H_FULL,
            BOX_SIZING_BORDER,
            // ZK Borderlayout will use hflex/vflex for responsive behavior
            // CSS provides base constraints
            "min-height: 600px;",
            BREAKPOINT_TABLET + " { min-height: 500px; }",
            BREAKPOINT_MOBILE + " { min-height: 400px; }"
        );
    }

    /**
     * ZK-native responsive column layout (left/right columns)
     * Implements Mondrian pattern for adaptive column sizing
     */
    public static String getZKResponsiveColumnLayout() {
        return combine(
            FLEX,
            W_FULL,
            getGapStandard(),
            BOX_SIZING_BORDER,
            // ZK components use hflex for responsive column behavior
            BREAKPOINT_LAPTOP + " { flex-direction: column; }",
            BREAKPOINT_TABLET + " { flex-direction: column; }"
        );
    }

    /**
     * ZK-native responsive statistics panel
     * Adapts layout based on ClientInfoEvent device detection
     */
    public static String getZKResponsiveStatsPanel() {
        return combine(
            FLEX,
            "justify-content: space-between;",
            "align-items: center;",
            W_FULL,
            BOX_SIZING_BORDER,
            // ZK handles responsive stats layout via hflex
            BREAKPOINT_TABLET + " { flex-direction: column; align-items: stretch; gap: 12px; }",
            BREAKPOINT_MOBILE + " { flex-direction: column; align-items: stretch; gap: 8px; }"
        );
    }

    // ========== ENHANCED FORM INPUT METHODS ==========

    /**
     * Standard editable input field style - Enhanced with mobile responsive
     */
    public static String getInputEditable() {
        return combine(
                getZKResponsiveFormField(), // Use ZK-responsive form field base
                BORDER,
                "border-radius: 8px;", // More rounded
                "background-color: white;",
                "box-sizing: border-box;",
                "width: 100%;", // Full width for mobile
                "transition: all 0.2s ease-in-out;",
                // Mobile responsive - prevent zoom on iOS
                BREAKPOINT_MOBILE + " { height: 40px; padding: 10px 14px; font-size: 16px; }");
    }

    /**
     * IMPROVED: PO Combobox with clean appearance - no ">" symbol, centered text
     * Fixed: Removed outline border on focus and improved click area handling
     */
    public static String getPOComboboxImproved() {
        return combine(
                // Base styling same as other input fields
                "width: 100%;",
                "height: 36px;",
                "border: 1px solid #d1d5db;",
                "border-radius: 6px;",
                "background-color: white;",
                "box-sizing: border-box;",
                "cursor: pointer;",
                "transition: all 0.2s ease-in-out;",
                TEXT_3XL,
                "padding: 8px 12px;", // Restore padding for proper text positioning
                // Center align text
                "text-align: center;",
                "display: flex;",
                "align-items: center;",
                "justify-content: center;",
                // Hover and focus states for better UX - FIXED: Remove ALL blue outlines and borders
                ":hover { border-color: #9ca3af; background-color: #ffffff; }",
                ":focus { border-color: #d1d5db; box-shadow: none !important; outline: none !important; border: 1px solid #d1d5db !important; }",
                ":focus-visible { outline: none !important; }",
                ":active { background-color: #e9ecef; }",
                // Additional visual feedback when clickable
                "pointer-events: auto !important;",
                "z-index: 1;");
    }

    /**
     * Global override for ZK combobox popup items to ensure readable font size
     * and comfortable item height/padding. This helps when ZK theme CSS
     * has higher specificity; use with caution.
     */
    public static String getComboboxPopupGlobalOverride() {
    return combine(
        // Broadly target all elements inside the combobox popup as some ZK themes
        // wrap label text inside additional nodes. Keep the po-comboitem sclass too.
        ".z-combobox-popup, .z-combobox-popup * , .z-combobox-popup .z-comboitem.po-comboitem, .po-comboitem {",
        "    font-size: 24px !important;",  // Consistent 24px font size
        "    height: 48px !important;",     // Comfortable height for 24px text
        "    padding: 12px 14px !important;", // Adequate padding
        "    line-height: 24px !important;", // Proper line height
        "    font-weight: bold !important;",
        "    background-color: white !important;",
        "    color: #111 !important;",
        "    border: none !important;",
        "    display: block !important;",
        "    width: 100% !important;",
        "    box-sizing: border-box !important;",
        "}",
        // Clear highlighting for selected items with different background colors
        ".z-combobox-popup .z-comboitem.z-comboitem-selected, .z-combobox-popup .z-comboitem-selected {",
        "    background-color: #007bff !important;", // Blue background for selected
        "    color: white !important;", // White text for contrast
        "    font-weight: bold !important;",
        "    border: 2px solid #0056b3 !important;", // Darker blue border
        "    box-shadow: inset 0 0 4px rgba(0,0,0,0.2) !important;", // Inner shadow
        "}",
        // Hover effect with different background color
        ".z-combobox-popup .z-comboitem:hover:not(.z-comboitem-selected) {",
        "    background-color: #ffffff !important;", // Light gray hover
        "    color: #111 !important;",
        "}"
    );
    }

    /**
     * Enhanced form field with better validation styling
     */
    public static String getEnhancedFormFieldWithValidation() {
        return combine(
                getEnhancedFormField(),
                "position: relative;" // For validation messages positioning
        );
    }

    /**
     * Success validation style
     */
    public static String getValidationSuccess() {
        return "border-color: #10b981; box-shadow: 0 0 0 3px rgba(16, 185, 129, 0.1);";
    }

    /**
     * Error validation style
     */
    public static String getValidationError() {
        return "border-color: #dc3545; box-shadow: 0 0 0 3px rgba(220, 53, 69, 0.1);";
    }

    // ========== COMPONENT STYLES ==========

    /**
     * Primary button style (Blue)
     */
    public static String getButtonPrimary() {
        return combine(
                BG_BLUE_600,
                TEXT_WHITE,
                BORDER_NONE,
                getZKResponsiveButton(), // Use ZK-responsive button base
                FONT_BOLD,
                CURSOR_POINTER,
                SHADOW_LG,
                "min-width: 120px;", // FIXED: Adequate width
                "height: 36px;", // FIXED: Standard button height
                "font-weight: bold;",
                "white-space: nowrap;", // FIXED: Prevent text wrap
                "flex-shrink: 0;" // FIXED: Don't shrink button
                );
    }

    /**
     * Danger button style (Red)
     */
    public static String getButtonDanger() {
        return combine(
                BG_RED_600,
                TEXT_WHITE,
                BORDER_NONE,
                "padding: 4px 8px;",
                ROUNDED_SM,
                FONT_BOLD,
                "font-size: 11px;",
                CURSOR_POINTER);
    }

    /**
     * Secondary button style (Gray) - Standard design
     */
    public static String getButtonSecondary() {
        return combine(
                BG_GRAY_600,
                TEXT_WHITE,
                BORDER_NONE,
                getZKResponsiveButton(), // Use ZK-responsive button base
                FONT_BOLD,
                CURSOR_POINTER,
                SHADOW_SM,
                "min-width: 120px;");
    }

    /**
     * Standard input field style - Consistent height and width with enhanced
     * styling
     */
    public static String getInputStandard() {
        return combine(
                W_FULL,
                "max-width: 200px;", // Increased max width for wider inputs
                "height: 36px;", // Increased height for better appearance
                "padding: 8px 12px;", // Increased padding for better look
                "font-size: 13px;", // Larger font for better readability
                BORDER,
                "border-radius: 6px;", // More rounded corners
                BOX_SIZING_BORDER);
    }

    /**
     * Card container style - Consistent alignment
     */
    public static String getCard() {
        return combine(
                BG_WHITE,
                BORDER_GRAY_300,
                "margin: 0;", // Remove margin for alignment
                "padding: 0;", // Remove padding, let parent handle padding
                ROUNDED_MD,
                SHADOW_SM);
    }

    /**
     * Statistics card style - Mobile responsive compact version
     */
    public static String getStatCardCompact() {
        return combine(
                "background: white;",
                "border: 1px solid #ddd;",
                "border-radius: 8px;",
                "padding: 12px;",
                "text-align: center;",
                "min-width: 100px;",
                "flex: 1;",
                "box-shadow: 0 1px 3px rgba(0,0,0,0.1);",
                // Mobile: full width for proper stacking and reduced padding
                "@media (max-width: 768px) { padding: 12px; min-width: 0; flex: 0 0 auto; width: 100%; margin: 0; }");
    }

    /**
     * First stat card (Scan/Jam) - Mobile order 1
     */
    public static String getStatCardFirst() {
        return combine(
                getStatCardCompact(),
                // Mobile: ensure first position
                "@media (max-width: 768px) { order: 1; }");
    }

    /**
     * Last stat card (Total) - Mobile order 3
     */
    public static String getStatCardLast() {
        return combine(
                getStatCardCompact(),
                // Mobile: ensure last position
                "@media (max-width: 768px) { order: 3; }");
    }

    /**
     * Standard label style
     */
    public static String getLabel() {
        return combine(
                FONT_BOLD,
                TEXT_GRAY_800,
                TEXT_SM,
                MB_2,
                DISPLAY_BLOCK);
    }

    /**
     * Title label style with comfortable spacing
     */
    public static String getTitleLabel() {
        return combine(
                TEXT_2XL,
                FONT_BOLD,
                TEXT_GRAY_800,
                "margin: 8px 0;" // Add vertical margin for better spacing
        );
    }

    /**
     * Info panel title style
     */
    public static String getInfoTitle() {
        return combine(
                TEXT_3XL,
                FONT_BOLD,
                TEXT_GRAY_800,
                "margin-bottom: 15px;",
                DISPLAY_BLOCK);
    }

    /**
     * Digital clock style with comfortable spacing
     */
    public static String getDigitalClock() {
        return combine(
                TEXT_BASE,
                TEXT_GRAY_600,
                FONT_BOLD,
                BG_BLUE_50,
                "padding: 8px 16px;", // Increased padding for better appearance
                ROUNDED,
                BORDER_BLUE_300,
                "margin: 4px 0;" // Add vertical margin
        );
    }

    /**
     * Statistics number style - Compact version
     */
    public static String getStatNumber(String color) {
        return combine(
                "font-size: 36px;", // Reduced from 42px for compact layout
                FONT_BOLD,
                color,
                LINE_HEIGHT_1,
                DISPLAY_BLOCK,
                TEXT_SHADOW);
    }

    /**
     * Statistics title style - Standard version
     */
    public static String getStatTitle() {
        return combine(
                "font-size: 12px;", // Standard small font
                FONT_BOLD,
                TEXT_GRAY_800,
                "margin-bottom: 8px;", // Standard margin
                DISPLAY_BLOCK);
    }

    /**
     * Table cell style
     */
    public static String getTableCell() {
        return combine(
                P_2,
                TEXT_XS);
    }

    /**
     * Table cell center style
     */
    public static String getTableCellCenter() {
        return combine(
                getTableCell(),
                TEXT_CENTER);
    }

    /**
     * Table cell bold style
     */
    public static String getTableCellBold() {
        return combine(
                getTableCell(),
                FONT_BOLD);
    }

    /**
     * Table cell center bold style
     */
    public static String getTableCellCenterBold() {
        return combine(
                getTableCellCenter(),
                FONT_BOLD);
    }

    /**
     * Barcode cell style (Monospace font)
     */
    public static String getTableCellBarcode() {
        return combine(
                "font-family: 'Courier New', monospace;",
                FONT_BOLD,
                P_2,
                TEXT_XS);
    }

    /**
     * Total row style
     */
    public static String getTableTotalRow() {
        return combine(
                BG_GRAY_50,
                FONT_BOLD,
                BORDER_TOP_2);
    }

    /**
     * Form grid container style - Enhanced mobile responsive for 557px
     */
    public static String getFormGrid() {
        return combine(
                GRID,
                GRID_COLS_2,
                "gap: 16px 18px;", // Desktop gaps
                W_FULL,
                "box-sizing: border-box;",
                // Mobile responsive - Single column on small screens
                "@media (max-width: 600px) { grid-template-columns: 1fr; gap: 12px; padding: 0; }");
    }

    /**
     * Form field container style
     */
    public static String getFormField() {
        return combine(
                FLEX_COL,
                MIN_W_0);
    }

    /**
     * Flex container with items centered
     */
    public static String getFlexCenter() {
        return combine(
                FLEX,
                ITEMS_CENTER,
                W_FULL);
    }

    /**
     * Layout section style with configurable height
     */
    public static String getLayoutSection(String height) {
        return combine(
                W_FULL,
                FLEX_1,
                FLEX_COL,
                height != null ? "min-height: " + height + ";" : "");
    }

    /**
     * Info message style
     */
    public static String getInfoMessage() {
        return combine(
                TEXT_GRAY_500,
                "font-size: 15px;",
                LINE_HEIGHT_RELAXED);
    }

    // ========== MODAL & LAYOUT STYLES ==========

    /**
     * Title bar style with improved vertical spacing
     */
    public static String getTitleBar() {
        return combine(
                FLEX,
                JUSTIFY_BETWEEN,
                ITEMS_CENTER,
                "padding: 16px 20px;", // Increased top/bottom padding
                H_60,
                BOX_SIZING_BORDER);
    }

    /**
     * Form info section style - Optimized spacing for table alignment
     */
    public static String getFormInfoSection() {
        return combine(
                FLEX,
                "gap: 8px;", // Slightly increased gap for better alignment
                W_FULL,
                MIN_H_40);
    }

    /**
     * Left column style - Consistent alignment with table
     */
    // public static String getLeftColumn() {
    //     return combine(
    //             "flex: 0 0 auto;",
    //             "width: 780px;", // Increased to match table width visually
    //             "max-width: 780px;",
    //             "box-sizing: border-box;",
    //             "margin: 0;", // Remove margin for alignment
    //             "padding: 20px;", // Consistent padding
    //             // Mobile responsive for small screens (557px and below)
    //             "@media (max-width: 600px) { width: 100%; max-width: 100%; flex: 1 1 100%; padding: 16px; margin: 0; }",
    //             getCard());
    // }

    // Perbaiki getLeftColumn() untuk fleksibel pada laptop
    public static String getLeftColumn() {
        return combine(
            "flex: 0 0 auto;",
            "width: 780px;",
            "max-width: 780px;",
            BOX_SIZING_BORDER,
            getPaddingStandard(),
            getCard(),
            // Laptop: Shrink width dan allow flex
            BREAKPOINT_LAPTOP + " { width: 100%; max-width: 100%; flex: 1 1 100%; " + getPaddingStandard() + " }",
            // Tablet: Stack vertikal
            BREAKPOINT_TABLET + " { width: 100%; max-width: 100%; flex: 1 1 100%; order: 1; " + getPaddingStandard() + " }"
        );
    }

    /**
     * Right column style - Consistent alignment with table
     */
    // Perbaiki getRightColumn() serupa
    public static String getRightColumn() {
        return combine(
            "flex: 1;",
            "min-width: 300px;",
            getPaddingStandard(),
            getCard(),
            // Laptop: Shrink dan allow flex
            BREAKPOINT_LAPTOP + " { flex: 1 1 100%; min-width: 100%; " + getPaddingStandard() + " }",
            // Tablet: Stack vertikal
            BREAKPOINT_TABLET + " { flex: 1 1 100%; min-width: 100%; order: 2; " + getPaddingStandard() + " }"
        );
    }

    /**
     * PO input container with gap - Enhanced spacing for larger inputs
     */
    public static String getPOInputContainer() {
        return combine(
                FLEX,
                ITEMS_CENTER,
                W_FULL,
                "gap: 10px;" // Increased gap for larger inputs
        );
    }

    /**
     * Statistics container style - Mobile layout with button positioning support
     */
    public static String getStatsContainer() {
        return combine(
                "display: flex;",
                "align-items: center;",
                "gap: 20px;",
                "flex: 1;",
                "justify-content: flex-start;",
                // Mobile: column layout to stack vertically with proper ordering
                "@media (max-width: 768px) { flex-direction: column; align-items: stretch; gap: 12px; justify-content: flex-start; }");
    }

    /**
     * Button container style - Mobile centered positioning between stats
     */
    public static String getButtonContainer() {
        return combine(
                "display: flex;",
                "align-items: center;",
                "justify-content: flex-end;",
                "flex-shrink: 0;",
                "min-width: 150px;", // Reduced back since only one button
                // Mobile: full width and center positioning with proper order between stats
                "@media (max-width: 768px) { justify-content: center; min-width: 0; width: 100%; order: 2; margin: 8px 0; }");
    }

    /**
     * Stats panel style - Optimized for horizontal footer layout
     */
    public static String getStatsPanel() {
        return combine(
                "padding: 0;", // Remove padding to let footer container handle it
                BG_GRAY_50,
                H_FULL,
                BOX_SIZING_BORDER,
                "display: flex;",
                "align-items: center;" // Center content vertically
        );
    }

    /**
     * Modal window style
     */
    public static String getModalWindow() {
        return "border-radius: 8px;";
    }

    /**
     * Modal content style
     */
    public static String getModalContent() {
        return P_30;
    }

    /**
     * Modal title style
     */
    public static String getModalTitle() {
        return combine(
                TEXT_XL,
                FONT_BOLD,
                "color: #495057;",
                MB_15,
                TEXT_CENTER);
    }

    /**
     * Modal label style
     */
    public static String getModalLabel() {
        return combine(
                FONT_BOLD,
                TEXT_BASE,
                W_100,
                TEXT_RIGHT);
    }

    /**
     * Modal input style
     */
    public static String getModalInput() {
        return combine(
                W_250,
                P_2,
                TEXT_BASE);
    }

    /**
     * Modal button box style
     */
    public static String getModalButtonBox() {
        return combine(
                MT_25,
                W_FULL);
    }

    /**
     * Table row style
     */
    public static String getTableRow() {
        return combine(
            "border-bottom: 1px solid #eee;",
            "pointer-events: auto !important;", // Force enable pointer events on rows
            "cursor: pointer !important;" // Make sure cursor indicates clickable
        );
    }

    /**
     * Spacing 8px utility
     */
    public static String getSpacing8() {
        return "8px";
    }

    /**
     * Spacing 10px utility
     */
    public static String getSpacing10() {
        return "10px";
    }

    /**
     * Spacing 15px utility
     */
    public static String getSpacing15() {
        return "15px";
    }

    /**
     * Spacing 20px utility
     */
    public static String getSpacing20() {
        return "20px";
    }

    // ========== LAYOUT CONTAINER STYLES ==========

    /**
     * Center panel style - Consistent layout alignment
     */
    public static String getCenterPanel() {
        return combine(
                BG_GRAY_100,
                "padding: 20px;", // Consistent with column padding
                OVERFLOW_AUTO,
                "min-width: 0;", // Ensure center can shrink
                "box-sizing: border-box;",
                // Mobile responsive
                "@media (max-width: 600px) { padding: 16px; overflow-x: hidden; }",
                // Desktop besar (1920x1080+): nonaktifkan overflow auto untuk tampilan penuh
                "@media (min-width: 1920px) { overflow: visible; }");
    }

    /**
     * Center content container style - Consistent with overall layout
     */
    public static String getCenterContent() {
        return combine(
                "width: 100%;",
                "height: 100%;",
                "min-width: 0;",
                "overflow: hidden;",
                "padding: 0;", // Remove padding for consistent alignment
                "margin: 0;", // Remove margin for consistent alignment
                // Mobile: consistent with other sections
                "@media (max-width: 768px) { padding: 0; margin: 0; }");
    }

    /**
     * Compact main container for form inputs - Optimized for no scroll
     */
    public static String getCompactMainContainer() {
        return combine(
                W_FULL,
                "max-width: 350px;", // Optimized max width for balance
                BOX_SIZING_BORDER,
                "overflow: hidden;" // Prevent any overflow
        );
    }

    // ========== MODAL CONFIGURATION ==========

    // ========== LAYOUT SIZE CONFIGURATIONS ==========

    /**
     * North panel size configuration - Balanced header height
     */
    public static String getNorthPanelSize() {
        return "10px"; // Restored to comfortable size for better visibility
    }

    /**
     * South panel size configuration - Optimized for footer layout
     */
    public static String getSouthPanelSize() {
        return "110px"; // Reduced back since only Report Detail button in footer
    }

    /**
     * Table section height configuration - Enhanced untuk accommodates button
     * properly
     */
    public static String getTableSectionHeight() {
        return "360px"; // FIXED: Increased from 280px untuk provide adequate space for table + button
    }

    // ========== FOOTER LAYOUT STYLES ==========

    /**
     * Footer container style - Mobile responsive with centered button layout
     */
    public static String getFooterContainer() {
        return combine(
                "display: flex;",
                "flex-direction: row;",
                "justify-content: space-between;",
                "align-items: center;",
                "width: 100%;",
                "padding: 12px 16px;",
                "gap: 20px;",
                // Mobile: vertical stack layout with proper ordering
                "@media (max-width: 768px) { flex-direction: column; justify-content: flex-start; align-items: stretch; gap: 12px; padding: 12px 16px; }");
    }

    // ========== RESPONSIVE LAYOUT UTILITIES ==========

    /**
     * Responsive information panel - Comfortable spacing for better UX
     */
    public static String getResponsiveInfoPanel() {
        return combine(
                "flex: 1 1 auto;",
                "min-width: 300px;",
                "padding: 16px;", // Restored comfortable padding
                "box-sizing: border-box;",
                // Mobile responsive
                "@media (max-width: 768px) { min-width: 100%; padding: 12px; }");
    }

    /**
     * Compact form info section - Optimized for space efficiency
     */
    public static String getCompactFormInfoSection() {
        return combine(
                "display: flex;",
                "gap: 16px;",
                "padding: 12px;", // Reduced padding
                "background-color: white;",
                "border: 1px solid #e5e7eb;",
                "border-radius: 8px;",
                "margin-bottom: 8px;", // Reduced margin
                "min-height: 120px;", // Fixed compact height
                "max-height: 140px;", // Prevent expansion
                "@media (max-width: 768px) { flex-direction: column; gap: 12px; min-height: auto; }");
    }

    /**
     * Mobile responsive table container - NO CLICK EVENTS, visual feedback only
     */
    public static String getMobileResponsiveTable() {
        return combine(
                "width: 100%;",
                "max-width: 100%;",
                // Remove fixed height since we're using setRows(5)
                "overflow: auto;", // Both horizontal and vertical scroll
                "box-sizing: border-box;",
                "border-radius: 8px;",
                "border: 1px solid #e5e7eb;",
                "background-color: white;",
                "-webkit-overflow-scrolling: touch;", // Smooth scrolling on mobile
                // CRITICAL: Completely disable selection and interactions
                "-webkit-user-select: none;",
                "-moz-user-select: none;",
                "-ms-user-select: none;",
                "user-select: none;",
                "pointer-events: none;", // Disable all pointer events
                // But allow scrolling
                "overflow: auto !important;",
                // Visual feedback disabled
                ".z-listbox .z-listitem { cursor: default !important; pointer-events: none !important; }",
                ".z-listbox .z-listitem:hover { background-color: transparent !important; }",
                ".z-listbox .z-listcell { pointer-events: none !important; }",
                // Mobile responsive - better sizing for small screens
                "@media (max-width: 600px) { font-size: 14px; border-radius: 6px; }");
    }

    /**
     * Table with hover effects and enabled functionality (delete buttons work)
     */
    // public static String getMobileResponsiveTableWithHover() {
    //     return combine(
    //             "width: 100%;",
    //             "max-width: 100%;",
    //             "overflow: auto !important;", // Force scrolling enabled
    //             "box-sizing: border-box;",
    //             "border-radius: 8px;",
    //             "border: 1px solid #e5e7eb;",
    //             "background-color: white;",
    //             "-webkit-overflow-scrolling: touch;",
    //             // CRITICAL: Enable all pointer events and interactions
    //             "pointer-events: auto !important;", // Enable all pointer events
    //             // Enable hover effects and normal functionality
    //             ".z-listbox { pointer-events: auto !important; overflow: auto !important; }",
    //             ".z-listbox .z-listitem { pointer-events: auto !important; cursor: pointer !important; }",
    //             ".z-listbox .z-listitem:hover { background-color: #f8fafc !important; }",
    //             ".z-listbox .z-listcell { pointer-events: auto !important; transition: background-color 0.2s ease; }",
    //             ".z-listbox .z-listhead { pointer-events: auto !important; }",
    //             ".z-listbox .z-listheader { pointer-events: auto !important; }",
    //             // Enable scrollbars
    //             ".z-listbox::-webkit-scrollbar { width: 8px; height: 8px; }",
    //             ".z-listbox::-webkit-scrollbar-track { background: #f1f1f1; }",
    //             ".z-listbox::-webkit-scrollbar-thumb { background: #c1c1c1; border-radius: 4px; }",
    //             ".z-listbox::-webkit-scrollbar-thumb:hover { background: #a8a8a8; }",
    //             // Mobile responsive
    //             "@media (max-width: 600px) { font-size: 14px; border-radius: 6px; }");
    // }

    // Perbaiki getMobileResponsiveTableWithHover() untuk scroll yang lebih baik pada laptop
    public static String getMobileResponsiveTableWithHover() {
        return combine(
            "width: 100%;",
            "max-width: 100%;",
            "overflow: auto !important;",
            BOX_SIZING_BORDER,
            "border-radius: 8px;",
            "border: 1px solid #e5e7eb;",
            "background-color: white;",
            "-webkit-overflow-scrolling: touch;",
            "pointer-events: auto !important;",
           // Laptop: Pastikan scroll horizontal jika diperlukan
            BREAKPOINT_LAPTOP + " { overflow-x: auto; overflow-y: auto; }",
            // Tablet: Full responsive
            BREAKPOINT_TABLET + " { font-size: 14px; border-radius: 6px; overflow-x: auto; }",

            // Mobile: Allow vertical scroll, prevent horizontal
            "@media (max-width: 600px) { overflow-y: auto; overflow-x: hidden; }"
        );
    }

    public static String getResponsiveMainContainer() {
        return combine(
            W_FULL,
            "max-width: 1200px;", // Limit max width untuk laptop besar
            BOX_SIZING_BORDER,
            "margin: 0 auto;", // Center on larger screens
            BREAKPOINT_LAPTOP + " { max-width: 100%; margin: 0; }",
            BREAKPOINT_TABLET + " { max-width: 100%; margin: 0; padding: 8px; }"
        );
    }

    /**
     * Mobile responsive form grid
     */
    public static String getMobileFormGrid() {
        return combine(
                "display: grid;",
                "grid-template-columns: 1fr 1fr;",
                "gap: 8px 10px;",
                "width: 100%;",
                "box-sizing: border-box;",
                // Mobile: single column layout
                "@media (max-width: 768px) { grid-template-columns: 1fr; gap: 8px; }");
    }

    /**
     * Balanced layout section - Consistent width alignment
     */
    public static String getBalancedLayout() {
        return combine(
                "display: flex;",
                "width: 100%;",
                "max-width: 100vw;",
                "box-sizing: border-box;",
                "overflow: hidden;",
                "gap: 20px;", // Desktop gap
                "margin: 0;", // Remove margin for consistent alignment
                "padding: 0;", // Remove padding for consistent alignment
                // Mobile responsive: stack vertically on small screens (557px support)
                "@media (max-width: 600px) { flex-direction: column; gap: 12px; overflow: visible; }");
    }

    /**
     * Mobile viewport optimization styles for root container
     */
    public static String getMobileViewport() {
        return combine(
                "width: 100%;",
                "max-width: 100vw;",
                "overflow-x: hidden;",
                "box-sizing: border-box;",
                // Touch-friendly interactions
                "touch-action: manipulation;",
                "-webkit-text-size-adjust: 100%;",
                // Prevent zoom on input focus for mobile
                "@media (max-width: 768px) { font-size: 16px; }");
    }

    /**
     * Responsive input field container
     */
    public static String getResponsiveInputContainer() {
        return combine(
                "width: 100%;",
                "min-width: 0;", // Allow shrinking
                "box-sizing: border-box;",
                // Mobile responsive behavior
                "@media (max-width: 768px) { min-width: 100%; }");
    }

    /**
     * Touch-friendly button spacing for mobile
     */
    public static String getMobileButtonSpacing() {
        return combine(
                "min-height: 44px;", // iOS touch target requirement
                "min-width: 44px;",
                "margin: 4px;",
                "padding: 8px 16px;",
                "box-sizing: border-box;",
                // Mobile: larger touch targets
                "@media (max-width: 768px) { min-height: 48px; min-width: 48px; padding: 12px 20px; }");
    }

    // ========== ENHANCED FORM LAYOUT STYLES ==========

    /**
     * Enhanced form grid with better alignment and responsive behavior - 2x2 layout
     */
    // public static String getEnhancedFormGrid() {
    //     return combine(
    //             "display: grid;",
    //             "grid-template-columns: 1fr 1fr;", // Back to 2 columns layout
    //             "gap: 12px 20px;", // Increased horizontal gap for better spacing
    //             "width: 100%;",
    //             "max-width: 760px;", // Adjusted to align inner input widths with left column padding
    //             "box-sizing: border-box;",
    //             // Mobile: single column layout
    //             "@media (max-width: 768px) { grid-template-columns: 1fr; gap: 10px; max-width: 100%; }");
    // }

    // Perbaiki getEnhancedFormGrid() untuk lebih responsive
    public static String getEnhancedFormGrid() {
        return combine(
            GRID,
            "grid-template-columns: 1fr 1fr;", // Default 2 kolom
            getGapStandard(),
            W_FULL,
            "max-width: 760px;",
            BOX_SIZING_BORDER,
            // Laptop: 2 kolom tapi dengan gap lebih kecil
            BREAKPOINT_LAPTOP + " { grid-template-columns: 1fr 1fr; gap: 10px; max-width: 100%; }",
            // Tablet/Mobile: 1 kolom
            BREAKPOINT_TABLET + " { grid-template-columns: 1fr; gap: 8px; max-width: 100%; }"
        );
    }

    /**
     * Form field container with improved spacing
     */
    public static String getEnhancedFormField() {
        return combine(
                "display: flex;",
                "flex-direction: column;",
                "gap: 6px;", // Increased gap for better label spacing
                "width: 100%;",
                "min-width: 0;", // Allow shrinking
                "box-sizing: border-box;");
    }

    /**
     * Wide form field that spans 2 columns (for tenant and organizer)
     */
    public static String getWideFormField() {
        return combine(
                "display: flex;",
                "flex-direction: column;",
                "gap: 6px;", // Match other fields
                "width: 100%;",
                "min-width: 0;",
                "box-sizing: border-box;",
                "grid-column: span 1;", // Normal span for 2x2 layout
                // Mobile: normal single column behavior
                "@media (max-width: 768px) { grid-column: span 1; }");
    }

    /**
     * PO input container with proper button alignment
     */
    public static String getEnhancedPOInputContainer() {
        return combine(
                "display: flex;",
                "align-items: stretch;",
                "gap: 8px;",
                "width: 100%;",
                "box-sizing: border-box;");
    }

    /**
     * PO input field that adjusts to container with wider styling
     */
    public static String getPOInputField() {
        return combine(
                "flex: 1;", // Take remaining space
                "min-width: 0;", // Allow shrinking
                "width: 100%;", // Full width within container
                "height: 36px;", // Slightly taller for better appearance
                "padding: 8px 12px;", // Increased padding for better look
                "border: 1px solid #d1d5db;",
                "border-radius: 6px;", // Slightly more rounded
                "font-size: 13px;", // Larger font for better readability
                "background-color: #f9fafb;",
                "color: #6b7280;",
                "font-weight: bold;", // Match readonly style
                "box-sizing: border-box;");
    }

    /**
     * Add button with proper sizing to match input height
     */
    public static String getAddButton() {
        return combine(
                "padding: 0;",
                "border: none;",
                "border-radius: 6px;",
                "background-color: #10b981;",
                "color: white;",
                "font-size: 18px;",
                "font-weight: bold;",
                "cursor: pointer;",
                "display: flex;",
                "align-items: center;",
                "justify-content: center;",
                "box-sizing: border-box;",
                "transition: background-color 0.2s;");
        // Width and height managed by wrapper container
    }

    /**
     * Barcode input field with enhanced styling - Mobile responsive
     */
    public static String getEnhancedBarcodeInput() {
        return combine(
                getZKResponsiveFormField(), // Use ZK-responsive form field base
                "border: 2px solid #3b82f6;",
                "border-radius: 8px;", // Match other inputs
                "background-color: #fefce8;",
                "box-sizing: border-box;",
                "width: 100%;", // Full width for mobile
                "transition: border-color 0.2s, box-shadow 0.2s;",
                // Mobile responsive - prevent zoom on iOS
                BREAKPOINT_MOBILE + " { height: 40px; padding: 10px 14px; font-size: 16px; }");
    }

    /**
     * Form container with proper spacing and max-width for wider forms
     */
    public static String getEnhancedFormContainer() {
        return combine(
                "width: 100%;",
                "max-width: 920px;", // Increased max width to better match table container width
                "padding: 20px;", // Increased padding
                "box-sizing: border-box;",
                "overflow: hidden;",
                // Mobile: reduce padding and full width
                "@media (max-width: 768px) { max-width: 100%; padding: 16px; }");
    }

    /**
     * Compact Add button style - Clean architecture, smaller size
     */
    public static String getAddButtonCompact() {
        return combine(
                "width: 32px;",
                "height: 32px;",
                "padding: 0;",
                "margin: 0;",
                "border: none;",
                "border-radius: 6px;",
                "background-color: #10b981;",
                "color: white;",
                "font-size: 16px;",
                "font-weight: bold;",
                "cursor: pointer;",
                "display: inline-flex;",
                "align-items: center;",
                "justify-content: center;",
                "box-sizing: border-box;",
                "flex-shrink: 0;", // Prevent shrinking
                // Hover effect
                ":hover { background-color: #059669; }",
                // Mobile responsive
                "@media (max-width: 600px) { width: 36px; height: 36px; font-size: 18px; }");
    }

    /**
     * Read-only input field style - Enhanced with clear visual distinction
     */
    public static String getInputReadonly() {
        return combine(
                "padding: 8px 12px;",
                "border: 1px solid #d1d5db;",
                "border-radius: 6px;",
                "font-size: 14px;",
                "line-height: 1.5;",
                "background-color: #ffffff;", // Light gray background for readonly
                "color: #495057;", // Darker text color
                "cursor: not-allowed;", // Show that field is not editable
                "box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.05);",
                "transition: none;" // Remove transition effects for readonly
        );
    }
    /**
     * Mobile optimized form grid - 3 columns on desktop, 1 column on mobile
     * Enhanced for Client ID, Organizer, Document No layout
     */
    public static String getMobileOptimizedFormGrid() {
        return combine(
                GRID,
                "grid-template-columns: repeat(3, 1fr);", // 3 equal columns on desktop
                "gap: 16px 12px;", // Vertical gap 16px, horizontal gap 12px
                W_FULL,
                BOX_SIZING_BORDER,
                // Mobile responsive: 1 column layout
                "@media (max-width: 768px) { grid-template-columns: 1fr; gap: 12px; }",
                // Tablet responsive: 2 columns layout
                "@media (min-width: 769px) and (max-width: 1024px) { grid-template-columns: repeat(2, 1fr); gap: 14px 10px; }"
        );
    }
    public static String createPOFieldContainer() {
        return combine(
                "display: flex;",
                "flex-direction: column;",
                "gap: 6px;",
                "width: 100%;",
                "min-width: 0;",
                "box-sizing: border-box;");
    }

    public static String poInputContainer() {
        return combine(
                "display: flex;",
                "align-items: center;",
                "gap: 8px;",
                "width: 100%;",
                "box-sizing: border-box;",
                "height: 36px;");
    }

    public static String buttonWrapper() {
        return combine(
                "flex: 0 0 auto;",
                "width: 32px;",
                "height: 32px;",
                "display: flex;",
                "align-items: center;",
                "justify-content: center;");
    }
    
    public static String tableContainer(){
        return combine(
            "flex: 1;",
                "display: flex;",
                "flex-direction: column;",
                "max-height: 350px;", // FIXED: Increased height untuk accommodates button
                "border: 1px solid #ddd;",
                "border-radius: 6px;",
                "background: white;",
                "overflow: hidden;"
        );
    }
    
    public static String tableWrapper(){
        return combine(
            "flex: 1;",
                "overflow-y: auto;",
                "overflow-x: hidden;", // Prevent all scrolling, let paging handle navigation
                "min-height: 0;",
                "pointer-events: auto !important;"
        );
    }
    public static String postingSection(){
        return combine(
            "flex: 0 0 auto;", // FIXED: Don't shrink this section
                "height: 56px;", // FIXED: Fixed height untuk button section
                "display: flex;",
                "justify-content: space-between;", // Changed from flex-end to space-between
                "align-items: center;",
                "padding: 10px 16px;", // FIXED: Balanced padding
                "background-color: #ffffff;",
                "border-top: 1px solid #ddd;",
                "border-radius: 0 0 6px 6px;" // FIXED: Round bottom corners only
        );
    }
    public static String contentContainer(){
        return combine(
            "padding: 15px;",
            "box-sizing: border-box;"
        );
    }
    public static String titleDialogPO(){
        return combine(
            "font-size: 16px;",
            "font-weight: bold;",
            "color: #495057;",
            "margin-bottom: 12px;",
            "text-align: center;",
            "display: block;"
        );
    }
    public static String poBoxDialog(){
        return combine(
            "margin-bottom: 10px;",
            "width: 100%;"
        );
    }
    public static String poLabelDialog(){
        return combine(
            "font-weight: bold;",
            "font-size: 13px;",
            "width: 85px;",
            "text-align: right;",
            "margin-right: 8px;"
        );
    }

    // width: 200px; padding: 6px; font-size: 13px; border: 1px solid #ccc; border-radius: 4px;
    public static String poInputDialog(){
        return combine(
            "width: 200px;",
            "padding: 6px;",
            "font-size: 13px;",
            "border: 1px solid #ccc;",
            "border-radius: 4px;"
        );
    }

    /**
     * Global override for ZK combobox to completely remove ALL blue outlines and borders
     * This targets all combobox instances in the application
     */
    public static String getComboboxGlobalOverride() {
        return combine(
            // Target all ZK combobox elements
            ".z-combobox, .z-combobox * {",
            "    outline: none !important;",
            "    border-color: #d1d5db !important;",
            "    box-shadow: none !important;",
            "}",
            // Focus states - completely remove any blue highlighting
            ".z-combobox.z-combobox-focus, .z-combobox:focus, .z-combobox:focus-within {",
            "    outline: none !important;",
            "    border-color: #d1d5db !important;",
            "    box-shadow: none !important;",
            "    border: 1px solid #d1d5db !important;",
            "}",
            // Input element inside combobox
            ".z-combobox .z-combobox-input {",
            "    outline: none !important;",
            "    border: none !important;",
            "    box-shadow: none !important;",
            "}",
            ".z-combobox .z-combobox-input:focus {",
            "    outline: none !important;",
            "    box-shadow: none !important;",
            "}",
            // Button element inside combobox
            ".z-combobox .z-combobox-button {",
            "    outline: none !important;",
            "    border: none !important;",
            "    box-shadow: none !important;",
            "}",
            ".z-combobox .z-combobox-button:focus {",
            "    outline: none !important;",
            "    box-shadow: none !important;",
            "}",
            // Any nested elements
            ".z-combobox *:focus {",
            "    outline: none !important;",
            "    box-shadow: none !important;",
            "}"
        );
    }
}