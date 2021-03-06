package <%= packageName %>.service.impl;

import <%= packageName %>.entity.<%= entityName %>;
import <%= packageName %>.repository.<%= entityName %>Repository;
import <%= packageName %>.model.<%= entityName %>Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import <%= packageName %>.service.<%= entityName %>Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class <%= entityName %>ServiceImpl implements <%= entityName %>Service {

    private final <%= entityName %>Repository <%= entityVarName %>Repository;

    @Autowired
    public <%= entityName %>ServiceImpl(<%= entityName %>Repository <%= entityVarName %>Repository) {
        this.<%= entityVarName %>Repository = <%= entityVarName %>Repository;
    }

    public List<<%= entityName %>> findAll<%= entityName %>s() {
        return <%= entityVarName %>Repository.findAll();
    }

    public Optional<<%= entityName %>> find<%= entityName %>ById(Long id) {
        return <%= entityVarName %>Repository.findById(id);
    }

    public <%= entityName %> save<%= entityName %>(<%= entityName %>Request <%= entityVarName %>Request) {
        ModelMapper modelMapper = new ModelMapper();
        <%= entityName %> <%= entityVarName %> = modelMapper.map(<%= entityVarName %>Request, <%= entityName %>.class);
        return <%= entityVarName %>Repository.save(<%= entityVarName %>);
    }

    public <%= entityName %> update<%= entityName %>(<%= entityName %>Request <%= entityVarName %>Request) {
        ModelMapper modelMapper = new ModelMapper();
        <%= entityName %> <%= entityVarName %> = modelMapper.map(<%= entityVarName %>Request, <%= entityName %>.class);
        return <%= entityVarName %>Repository.save(<%= entityVarName %>);
    }

    public void delete<%= entityName %>ById(Long id) {
        <%= entityVarName %>Repository.deleteById(id);
    }
}