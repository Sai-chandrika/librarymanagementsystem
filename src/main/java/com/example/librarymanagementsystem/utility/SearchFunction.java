//package com.example.librarymanagementsystem.utility;
//
//
//import org.springframework.stereotype.Service;
//
//import java.lang.reflect.Field;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @Author ➤➤➤ ANIL GOUD SIRIGE
// * @Date ➤➤➤ 26/08/23
// * @Time ➤➤➤ 12:00 pm
// * @Project ➤➤➤ thrymr-spring-boot-generic-app
// */
//@Service
//public class SearchFunction {
//
//    public boolean search(Object object, int layer, String searchKey) {
//
//        StringBuilder sb = new StringBuilder();
//        int actualCount = 0;
//        // Keep track of processed objects to prevent circular references
//        Set<Object> processedObjects = new HashSet<>();
//        return appendFields(object, sb, processedObjects, actualCount, layer, searchKey);
//
//    }
//
//    private boolean appendFields(Object obj, StringBuilder sb, Set<Object> processedObjects, int count, int originalCount, String searchKey) {
//
//        processedObjects.add(obj);
//
//        Field[] fields = obj.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            try {
//                String fieldName = field.getName();
//                Object value = field.get(obj);
//                if (fieldName.startsWith("$$_") || fieldName.startsWith("cachedValue$") || value == null) {
//                    // Skip Hibernate-specific fields and nulls
//                    continue;
//                }
//                if (value instanceof Collection) {
//                    // Handle collection fields
//                    if (count <= originalCount - 1) {
//                        count = count + 1;
//                        for (Object item : (Collection<?>) value) {
//                            if (item instanceof BaseEntity || item.getClass().getPackageName().contains("com.example.thrymr.spring.boot.app.dto")) {
//                                // Recursively call appendFields() on related BaseEntity
//                                if (!processedObjects.contains(value) && appendFields(item, sb, processedObjects, count, originalCount, searchKey))
//                                    return true;
//                            } else {
//                                if (String.valueOf(item).toLowerCase().contains(searchKey.toLowerCase())) return true;
//                                sb.append(value);
//                            }
//                        }
//                    }
//                } else if (value instanceof BaseEntity || value.getClass().getPackageName().contains("com.example.thrymr.spring.boot.app.dto")) {
//                    // Recursively call appendFields() on related BaseEntity
//                    if (count < originalCount - 1 && !processedObjects.contains(value)) {
//                        count = count + 1;
//                        if (appendFields(value, sb, processedObjects, count, originalCount, searchKey)) return true;
//                        count = count - 1;
//                    }
//                } else {
//                    if (String.valueOf(value).toLowerCase().contains(searchKey.toLowerCase())) return true;
//                    sb.append(value);
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }
//}
