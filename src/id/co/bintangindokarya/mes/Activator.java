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
import org.osgi.framework.BundleContext;

import id.co.bintangindokarya.mes.form.ProductionAssemblingForm;
import id.co.bintangindokarya.mes.form.ProductionSewingForm;

/**
 * 
 * @author Erick
 *
 */
public class Activator extends Incremental2PackActivator {
	
	@Override
	public void start(BundleContext context) throws Exception {
		Core.getMappedModelFactory().scan(context, "id.co.bintangindokarya.mes.model");
		Core.getMappedProcessFactory().scan(context, "id.co.bintangindokarya.mes.process");
		Core.getMappedColumnCalloutFactory().scan(context, "id.co.bintangindokarya.mes.callout");

//		IMappedFormFactory mappedFormFactory = Extensions.getMappedFormFactory();
//		mappedFormFactory.addMapping(ProductionOutputAssemblingForm.class.getName(), () -> new ProductionOutputAssemblingForm().getForm());
//		mappedFormFactory.addMapping(WWFActivity.class.getName(), () -> new WWFActivity().getForm());
		
		IMappedFormFactory mappedFormFactory = Extensions.getMappedFormFactory();

        // Hanya map form kamu yang benar-benar implement ADForm
        mappedFormFactory.addMapping(ProductionAssemblingForm.class.getName(),
            () -> new ProductionAssemblingForm().getForm());
        
        mappedFormFactory.addMapping(ProductionSewingForm.class.getName(),
                () -> new ProductionSewingForm().getForm());
        
		super.start(context);
	}
}
