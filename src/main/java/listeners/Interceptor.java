package listeners;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Interceptor implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        int methCount = methods.size();
        List<IMethodInstance> result = new ArrayList<IMethodInstance>();
        ArrayList<String> groupsList = new ArrayList<String>();
        ArrayList<String> classGroups = new ArrayList<>();
        ArrayList<String> methodGroups = new ArrayList<>();
        for (int i = 0; i < methCount; i++) {
            IMethodInstance instns = methods.get(i);
            try {
                groupsList = new ArrayList<>(Arrays.asList(getGroupsFromVmOptions()));
                classGroups = new ArrayList<>(Arrays.asList(instns.getMethod().getConstructorOrMethod().getDeclaringClass().getAnnotation(Test.class).groups()));
                methodGroups = new ArrayList<>(Arrays.asList(instns.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).groups()));
                classGroups.addAll(methodGroups);
            } catch (NullPointerException e) {}

            if (groupsList == null || classGroups.containsAll(groupsList)) {
                result.add(instns);
            }
        }
        return result;
    }

    private String[] getGroupsFromVmOptions() {

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMXBean.getInputArguments();
        String groups = null;
        String[] masOfGroups = null;
        try {
            groups = arguments.stream().filter(argument -> argument.contains("-Dgroups=")).findAny().get();
            masOfGroups = groups.replace("-Dgroups=", "").split(",");
        } catch (NoSuchElementException e) {}
        return masOfGroups;
    }
}
