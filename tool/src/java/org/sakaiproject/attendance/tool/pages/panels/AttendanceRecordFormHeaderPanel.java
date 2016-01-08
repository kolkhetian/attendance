/*
 *  Copyright (c) 2016, The Apereo Foundation
 *
 *  Licensed under the Educational Community License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *              http://opensource.org/licenses/ecl2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.sakaiproject.attendance.tool.pages.panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;

/**
 * Created by Leonardo Canessa [lcanessa1 (at) udayton (dot) edu]
 */
public class AttendanceRecordFormHeaderPanel extends BasePanel {
    private boolean areRecordsOfStudents;

    public AttendanceRecordFormHeaderPanel(String id, boolean iS) {
        super(id);
        this.areRecordsOfStudents = iS;

        add(createItemLabel());
    }

    private Label createItemLabel() {
        Label itemLabel;
        if(areRecordsOfStudents) {
            itemLabel = new Label("item-type", new ResourceModel("attendance.record.form.header.student"));
        } else {
            itemLabel = new Label("item-type", new ResourceModel("attendance.record.form.header.event"));
        }

        return itemLabel;
    }
}
