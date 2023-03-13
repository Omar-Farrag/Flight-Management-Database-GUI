package DatabaseManagement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class ConstraintChecker implements ConstraintChecks {

    private String metaDataFile = "Metadata.json";
    public JSONArray metaData;
    private HashMap<Constraint, ValidationFunction> C_constraint_to_validator;
    private HashMap<Constraint, ValidationFunction> dataType_to_validator;
    private static ConstraintChecker instance;

    public ConstraintChecker getInstance() {
        return instance == null ? instance = new ConstraintChecker() : instance;
    }

    private ConstraintChecker() {
        readMetaDataFromFile();
        C_constraint_to_validator = new HashMap<>();
        dataType_to_validator = new HashMap<>();

        C_constraint_to_validator.put(new Constraint(ConstraintEnum.CONSTRAINT1),
                (constraint) -> validateReferentialIntegrity());

        dataType_to_validator.put(new Constraint(ConstraintEnum.constraint1),
                (constraint) -> validateReferentialIntegrity());

    }

    private void readMetaDataFromFile() {
        try {
            FileInputStream fin = new FileInputStream(metaDataFile);
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader bin = new BufferedReader(in);

            String fileContent = "";
            String line;
            while ((line = bin.readLine()) != null)
                fileContent += line;

            metaData = (JSONArray) new JSONParser().parse(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Error check(Table t, HashMap<String, String> attributesToCheck)
            throws TableNotFoundException, AttributeNotFoundException, ConstraintNotFoundException {

        JSONObject table = getTableInfoFromMetaData(t.getTableName());
        JSONObject tableAttributes = (JSONObject) table.get("Attributes");

        for (String attribute : attributesToCheck.keySet()) {
            if (!tableAttributes.containsKey(attribute))
                throw new AttributeNotFoundException(t.getTableName(), attribute);

            JSONArray attributeConstraints = (JSONArray) tableAttributes.get(attribute);
            for (Object obj : attributeConstraints) {
                String constraint = (String) obj;

                if (constraint.startsWith("R_"))
                    validateReferentialIntegrity();
                else if (constraint.startsWith("P_"))
                    validatePrimaryKey();
                else if (constraint.startsWith("U_"))
                    validateUniqueness();
                else if (constraint.startsWith("C_"))
                    validateCheckCondition();

                else
                    validateType();

                // else if (!dataType_to_validator.containsKey(constraint))
                // throw new ConstraintNotFoundException(constraint);

                // ValidationFunction validator = constraint_to_validator.get(constraint);
                // validator.validate(constraint);
            }
        }

    }

    private JSONObject getTableInfoFromMetaData(String tableName) throws TableNotFoundException {
        for (Object table : metaData) {
            JSONObject tableObject = (JSONObject) table;
            if (tableObject.get("TableName").equals(tableName))
                return tableObject;
        }
        throw new TableNotFoundException();
    }
    // ///////////////////////////////////////////VALIDATORS/////////////////////////////////////////////////

    private String validateReferentialIntegrity() {
    }

    public static void main(String[] args) {
        ConstraintChecker cK = new ConstraintChecker();
        try {
            FileOutputStream fout = new FileOutputStream("Metadata2.json");
            OutputStreamWriter out = new OutputStreamWriter(fout);
            out.write(cK.metaData.toJSONString());
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
