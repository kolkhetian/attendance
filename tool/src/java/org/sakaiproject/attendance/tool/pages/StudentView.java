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

package org.sakaiproject.attendance.tool.pages;


import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.ResourceModel;
import org.sakaiproject.attendance.model.AttendanceRecord;
import org.sakaiproject.attendance.tool.dataproviders.AttendanceRecordProvider;
import org.sakaiproject.attendance.tool.pages.panels.AttendanceRecordFormDataPanel;
import org.sakaiproject.attendance.tool.pages.panels.AttendanceRecordFormHeaderPanel;

/**
 * Created by Leonardo Canessa [lcanessa1 (at) udayton (dot) edu]
 */
public class StudentView extends BasePage {
    private static final    long        serialVersionUID    = 1L;
    private                 String      studentId;
    private                 Long        previousEventId;
    private                 boolean     isStudent           = false;

    public StudentView() {
        this.studentId = sakaiProxy.getCurrentUserId();

        init();
    }
    public StudentView(String id, Long eventId) {
        this.studentId = id;
        this.previousEventId = eventId;

        init();
    }

    private void init() {
        if(this.role.equals("Student")){
            this.isStudent = true;
            hideNavigationLink(this.firstLink);
            hideNavigationLink(this.addEventLink);
        }

        add(createHeader());
        add(createTable());
    }

    private WebMarkupContainer createHeader() {
        WebMarkupContainer header = new WebMarkupContainer("header");
        header.setOutputMarkupPlaceholderTag(true);

        Link<Void> closeLink = new Link<Void>("close-link") {
            @Override
            public void onClick() {
                setResponsePage(new EventView(previousEventId, BasePage.OVERVIEW_PAGE));
            }

            @Override
            public boolean isVisible(){
                return !isStudent;
            }
        };
        closeLink.add(new Label("close-link-text", new ResourceModel("attendance.event.link.close")));
        header.add(closeLink);

        return header;
    }

    private WebMarkupContainer createTable(){
        WebMarkupContainer studentViewData = new WebMarkupContainer("student-view-data");

        studentViewData.add(new AttendanceRecordFormHeaderPanel("header", !isStudent));
        studentViewData.add(createData());

        return studentViewData;
    }

    private DataView<AttendanceRecord> createData(){
        DataView<AttendanceRecord> dataView = new DataView<AttendanceRecord>("records", new AttendanceRecordProvider(this.studentId)) {
            @Override
            protected void populateItem(Item<AttendanceRecord> item) {
                item.add(new AttendanceRecordFormDataPanel("record", item.getModel(), false));
            }
        };

        return dataView;
    }
}
