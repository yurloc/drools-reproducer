/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.brms.qe;

import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.utils.KieHelper;

public class Drools1368Test {

    @Test
    public void test() {
        String drl = "import " + Drools1368Test.BaseFact.class.getCanonicalName() + ";\n"
                + "import " + Drools1368Test.MyFact.class.getCanonicalName() + ";\n"
                + "rule R when\n"
                + "    $b : BaseFact( $n : name, name != null )\n"
                + "then end\n";
        KieSession ksession = new KieHelper().addContent(drl, ResourceType.DRL).build().newKieSession();

        MyFact f = new MyFact();
        FactHandle fh = ksession.insert(f);
        f.setName("hello");
        ksession.update(fh, f, "name");
    }

    public abstract class BaseFact {

        public abstract String getName();

        // uncomment this to prevent Unknown property exception
//        public abstract void setName(String name);
    }

    public class MyFact extends BaseFact {

        private String name;

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
