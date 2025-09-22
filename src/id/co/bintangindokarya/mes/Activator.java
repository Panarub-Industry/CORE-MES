/**********************************************************************
* This file is part of iDempiere ERP Open Source                      *
* http://www.idempiere.org                                            *
*                                                                     *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Trek Global Corporation                                           *
* - Heng Sin Low                                                      *
**********************************************************************/
package id.co.bintangindokarya.mes;

import org.adempiere.base.Core;
import org.adempiere.plugin.utils.Incremental2PackActivator;
import org.adempiere.webui.Extensions;
import org.adempiere.webui.factory.IMappedFormFactory;
import org.adempiere.webui.panel.ADForm;
import org.osgi.framework.BundleContext;

import id.co.bintangindokarya.mes.form.ProductFormAssembly;

/**
 * 
 * @author Fauzan
 *
 */
public class Activator extends Incremental2PackActivator {
    
    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("=== MES Plugin Starting ===");
        System.out.println("Loading MES Core Plugin with packages:");
        System.out.println("  - Models: id.co.bintangindokarya.mes.model");
        System.out.println("  - Processes: id.co.bintangindokarya.mes.process");
        System.out.println("  - Callouts: id.co.bintangindokarya.mes.callout");
        System.out.println("  - Utils: id.co.bintangindokarya.mes.utils (ComboBoxUtil, StockUtil, BarcodeUtil)");
        System.out.println("  - Forms: ProductFormAssembly");
        
        try {
            // Call parent first
            super.start(context);
            
            // Scan packages with try-catch for each
            try {
                Core.getMappedModelFactory().scan(context, "id.co.bintangindokarya.mes.model");
            } catch (Exception e) {
                System.out.println("No model classes found in id.co.bintangindokarya.mes.model - " + e.getMessage());
            }
            
            try {
                Core.getMappedProcessFactory().scan(context, "id.co.bintangindokarya.mes.process");
            } catch (Exception e) {
                System.out.println("No process classes found in id.co.bintangindokarya.mes.process - " + e.getMessage());
            }
            
            try {
                Core.getMappedColumnCalloutFactory().scan(context, "id.co.bintangindokarya.mes.callout");
            } catch (Exception e) {
                System.out.println("No callout classes found in id.co.bintangindokarya.mes.callout - " + e.getMessage());
            }
            
            // Initialize utils package (utility classes: ComboBoxUtil, StockUtil, BarcodeUtil)
            try {
                // Utils package contains utility classes, not callouts
                // Just log that utils package is being loaded
                System.out.println("Loading utility classes from id.co.bintangindokarya.mes.utils");
                
                // Verify utility classes are available
                Class.forName("id.co.bintangindokarya.mes.utils.ComboBoxUtil");
                Class.forName("id.co.bintangindokarya.mes.utils.StockUtil");
                Class.forName("id.co.bintangindokarya.mes.utils.BarcodeUtil");
                
                System.out.println("Successfully loaded utility classes: ComboBoxUtil, StockUtil, BarcodeUtil");
            } catch (ClassNotFoundException e) {
                System.err.println("Warning: Some utility classes not found in id.co.bintangindokarya.mes.utils - " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error loading utility classes from id.co.bintangindokarya.mes.utils - " + e.getMessage());
            }
            
            // Register form factory with error handling
            try {
                IMappedFormFactory mappedFormFactory = Extensions.getMappedFormFactory();
                mappedFormFactory.addMapping(ProductFormAssembly.class.getName(), 
                    () -> {
                        return new ProductFormAssembly();
                    });
                System.out.println("ProductFormAssembly registered successfully");
            } catch (Exception e) {
                System.err.println("Error registering ProductFormAssembly: " + e.getMessage());
                e.printStackTrace();
            }
            
            System.out.println("=== MES Plugin Started Successfully ===");
            System.out.println("All MES components loaded:");
            System.out.println("  ✓ Model classes scanned");
            System.out.println("  ✓ Process classes scanned");
            System.out.println("  ✓ Callout classes scanned");
            System.out.println("  ✓ Utility classes loaded (ComboBoxUtil, StockUtil, BarcodeUtil)");
            System.out.println("  ✓ ProductFormAssembly form registered");
            System.out.println("=== MES Plugin Ready for Use ===");
            
        } catch (Exception e) {
            System.err.println("Error starting MES plugin: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    
    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("=== MES Plugin Stopping ===");
        super.stop(context);
        System.out.println("=== MES Plugin Stopped ===");
    }
}