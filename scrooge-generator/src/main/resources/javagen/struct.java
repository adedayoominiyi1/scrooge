{{#public}}
package {{package}};

import com.twitter.scrooge.Utilities;
import com.twitter.scrooge.ThriftStruct;
import com.twitter.scrooge.ThriftStructCodec;
import com.twitter.util.Function2;
import org.apache.thrift.protocol.*;
import java.nio.ByteBuffer;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
{{#imports}}
import {{parentPackage}}.{{subPackage}}.*;
{{/imports}}

public {{/public}}{{^public}}static {{/public}}class {{name}}{{#isException}} extends Exception{{/isException}} implements ThriftStruct {
  private static final TStruct STRUCT = new TStruct("{{name}}");
{{#fields}}
  private static final TField {{fieldConst}} = new TField("{{name}}", TType.{{constType}}, (short) {{id}});
  private final {{#optional}}Utilities.Option<{{fieldType}}>{{/optional}}{{^optional}}{{fieldType}}{{/optional}} {{name}};
{{/fields}}

  public static class Builder {
{{#fields}}
    private {{primitiveFieldType}} _{{name}} = {{defaultReadValue}};
    private Boolean _got_{{name}} = false;

    public Builder {{name}}({{primitiveFieldType}} value) {
      this._{{name}} = value;
      this._got_{{name}} = true;
      return this;
    }
{{/fields}}

    public {{name}} build() {
{{#fields}}
{{#required}}
      if (!_got_{{name}})
      throw new IllegalStateException("Required field '{{name}}' was not found for struct {{struct}}");
{{/required}}
{{/fields}}
      return new {{name}}(
{{#fields}}
{{#optional}}
      Utilities.Option.make(this._got_{{name}}, this._{{name}}){{/optional}}
{{^optional}}
        this._{{name}}{{/optional}}
{{/fields|,
}}    );
    }
  }

  public static ThriftStructCodec<{{name}}> CODEC = new ThriftStructCodec<{{name}}>() {
    public {{name}} decode(TProtocol _iprot) throws org.apache.thrift.TException {
      Builder builder = new Builder();
{{#fields}}
      {{primitiveFieldType}} {{name}} = {{defaultReadValue}};
{{/fields}}
      Boolean _done = false;
      _iprot.readStructBegin();
      while (!_done) {
        TField _field = _iprot.readFieldBegin();
        if (_field.type == TType.STOP) {
          _done = true;
        } else {
          switch (_field.id) {
{{#fields}}
{{#readWriteInfo}}
            {{>readField}}
            builder.{{name}}({{name}});
{{/readWriteInfo}}
{{/fields}}
            default:
              TProtocolUtil.skip(_iprot, _field.type);
          }
          _iprot.readFieldEnd();
        }
      }
      _iprot.readStructEnd();
      try {
        return builder.build();
      } catch (IllegalStateException stateEx) {
        throw new TProtocolException(stateEx.getMessage());
      }
    }

    public void encode({{name}} struct, TProtocol oprot) throws org.apache.thrift.TException {
      struct.write(oprot);
    }
  };

  public static {{name}} decode(TProtocol _iprot) throws org.apache.thrift.TException {
    return CODEC.decode(_iprot);
  }

  public static void encode({{name}} struct, TProtocol oprot) throws org.apache.thrift.TException {
    CODEC.encode(struct, oprot);
  }

  public {{name}}(
{{#fields}}
  {{#optional}}Utilities.Option<{{fieldType}}>{{/optional}}{{^optional}}{{primitiveFieldType}}{{/optional}} {{name}}{{comma}}
{{/fields}}
  ) {
{{#isException}}
{{#fields}}
{{#isMessage}}
    super(message);
{{/isMessage}}
{{/fields}}
{{/isException}}
{{#fields}}
    this.{{name}} = {{name}};
{{/fields}}
  }

{{#fields}}
{{^isReserved}}
  {{primitiveFieldType}} get{{Name}}() {
    return this.{{name}}{{#optional}}.get(){{/optional}};
  }
{{/isReserved}}
{{/fields}}

  public void write(TProtocol _oprot) throws org.apache.thrift.TException {
    _oprot.writeStructBegin(STRUCT);
{{#fields}}
{{#readWriteInfo}}
    {{>writeField}}
{{/readWriteInfo}}
{{/fields}}
    _oprot.writeFieldStop();
    _oprot.writeStructEnd();
  }
}