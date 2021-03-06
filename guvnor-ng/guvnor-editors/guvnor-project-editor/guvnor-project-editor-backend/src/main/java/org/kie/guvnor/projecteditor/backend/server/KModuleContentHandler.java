/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.guvnor.projecteditor.backend.server;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.kie.guvnor.project.model.ClockTypeOption;
import org.kie.guvnor.project.model.KBaseModel;
import org.kie.guvnor.project.model.KModuleModel;
import org.kie.guvnor.project.model.KSessionModel;
import org.kie.guvnor.project.model.ListenerModel;
import org.kie.guvnor.project.model.QualifierModel;
import org.kie.guvnor.project.model.WorkItemHandlerModel;
import org.kie.guvnor.projecteditor.backend.server.converters.ClockTypeConverter;
import org.kie.guvnor.projecteditor.backend.server.converters.KBaseConverter;
import org.kie.guvnor.projecteditor.backend.server.converters.KModuleConverter;
import org.kie.guvnor.projecteditor.backend.server.converters.KSessionConverter;
import org.kie.guvnor.projecteditor.backend.server.converters.ListenerConverter;
import org.kie.guvnor.projecteditor.backend.server.converters.QualifierConverter;
import org.kie.guvnor.projecteditor.backend.server.converters.WorkItemHandelerConverter;

public class KModuleContentHandler {

    public KModuleModel toModel(String xml) {
        return (KModuleModel) createXStream().fromXML(xml);
    }

    public String toString(KModuleModel model) {
        return createXStream().toXML(model);
    }

    private XStream createXStream() {
        XStream xStream = new XStream(new DomDriver());

        xStream.registerConverter(new KModuleConverter());
        xStream.registerConverter(new KBaseConverter());
        xStream.registerConverter(new KSessionConverter());
        xStream.registerConverter(new ClockTypeConverter());
        xStream.registerConverter(new ListenerConverter());
        xStream.registerConverter(new QualifierConverter());
        xStream.registerConverter(new WorkItemHandelerConverter());

        xStream.alias("kmodule", KModuleModel.class);
        xStream.alias("kbase", KBaseModel.class);
        xStream.alias("ksession", KSessionModel.class);
        xStream.alias("clockType", ClockTypeOption.class);
        xStream.alias("listener", ListenerModel.class);
        xStream.alias("qualifier", QualifierModel.class);
        xStream.alias("workItemHandler", WorkItemHandlerModel.class);

        return xStream;
    }
}
