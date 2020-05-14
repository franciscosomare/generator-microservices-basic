package <%= packageName %>.service;

import <%= packageName %>.entity.<%= entityName %>;
import <%= packageName %>.model.<%= entityName %>Request;

import java.util.List;
import java.util.Optional;

public interface <%= entityName %>Service {

    public List<<%= entityName %>> findAll<%= entityName %>s();

    public Optional<<%= entityName %>> find<%= entityName %>ById(Long id);

    public <%= entityName %> save<%= entityName %>(<%= entityName %>Request <%= entityVarName %>Request);

    public <%= entityName %> update<%= entityName %>(<%= entityName %>Request <%= entityVarName %>Request);

    public void delete<%= entityName %>ById(Long id);
}
